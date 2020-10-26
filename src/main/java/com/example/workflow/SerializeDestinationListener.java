package com.example.workflow;

import com.example.pojo.Destination;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

import static org.camunda.spin.Spin.JSON;


@Component
public class SerializeDestinationListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        Destination destination = JSON(delegateExecution.getVariable("destination")).mapTo(Destination.class);
        delegateExecution.setVariable("country", destination.getCountry());
        delegateExecution.setVariable("state", destination.getState());
        delegateExecution.setVariable("city", destination.getCity());
        delegateExecution.setVariable("address", destination.getStreetAndNumber());
    }
}
