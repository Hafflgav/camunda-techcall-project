package com.example.workflow;

import com.example.pojo.User;
import com.example.pojo.UserList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@Component
public class CallAPIDelegate implements JavaDelegate {
    private final String ENDPOINT = "https://reqres.in/api/users?page=1";

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ArrayList<User>  userList= userRequest();
        for(User u : userList){
            delegateExecution.setVariable(String.valueOf(u.getId()), u.getFirst_name() +" "+ u.getLast_name() + " Email: "+ u.getEmail());
        }
        delegateExecution.setVariable("user", userList.get(0).getFirst_name() +" "+ userList.get(0).getLast_name() + " Email: "+ userList.get(0).getEmail());
    }

    public  ArrayList<User> userRequest() {
        String response="";
        try{
            URL url = new URL(ENDPOINT);
            response = httpGET(url);
        }catch (Exception ex){
            System.out.println(ex);
        }
        ArrayList<User> users = parseResponseToUser(response);

        return users;
    }

    public String httpGET(URL url){
        StringBuilder response = new StringBuilder();
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            bufferedReader.close();
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
        return response.toString();
    }

    public ArrayList<User> parseResponseToUser(String response){
        ObjectMapper mapper = new ObjectMapper();
        UserList users = new UserList();
        try {
            users = mapper.readValue(response, UserList.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return users.getData();
    }
}
