# Tech-Call-Project
## Summary
This project contains various examples for getting started with Camunda. 
Therefore, the Camunda Spring Boot approach is choosen as recommended [here](https://camunda.com/best-practices/deciding-about-your-stack/).
Inside this application we make use of Camunda's enterprise libraries. If you haven't got a valid license you can get a 30-day [trial](https://camunda.com/download/enterprise/).
Make sure your [.m2 settings](https://docs.camunda.org/get-started/apache-maven/#enterprise-edition-1) are correct.
 
The application is built in such a way, that each example is embedded in its own BPMN diagram.  
Currently, the examples below are implemented. Since this project is still under constructions more examples will follow soon. 

For further questions or remarks do not hesitate to contact me by [mail](mailto:thomas.heinrichs@camunda.com).

## Included Examples 
The current included examples are listed below with some additional explanations. 
For each example you should check out the corresponding BPMN file in [Camunda Modeler](https://camunda.com/products/camunda-bpm/modeler/).

### API Call 
In order to make an API call have a look into the tech-call-project and start the Spring Boot app. 
Afterwards you only need to start the "APICallingProcess" in order to make such a REST call. 
A simple HTTP client is contained in the referenced "CallAPIDelegate" file.

### JSON Start Event (Spin)
This example makes use of Spin in order to deserialize a JSON string into a POJO. 
Therefore, you need to import the content of "postman-export" into the Postman application which can be downloaded [here](https://www.postman.com/downloads/).
Start the Spring Boot app and trigger a process with Postman via REST. 

The corresponding implementation can be found in the "SerializeDestinationListener".

### External Task Worker
This example makes use of the [external task pattern](https://docs.camunda.org/manual/latest/user-guide/process-engine/external-tasks/). The client is contained inside the mail-service project.
Please start this application as well as the tech-call-project containing Camunda. By triggering the "ExternalTaskProcess" you can try out this approach. 

This approach is often used in case the business logic is written in another language (e.g. C#). This approach is completely agnostic to the language used. 