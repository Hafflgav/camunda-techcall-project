package com.example.workflow;

import com.example.pojo.Destination;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

import static org.camunda.spin.Spin.JSON;


@Component
public class SerializeDestinationListener implements ExecutionListener {

    private Logger logger = LoggerFactory.getLogger(SerializeDestinationListener.class.getName());

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        Destination destination = JSON(delegateExecution.getVariable("destination")).mapTo(Destination.class);
        delegateExecution.setVariable("country", destination.getCountry());
        delegateExecution.setVariable("state", destination.getState());
        delegateExecution.setVariable("city", destination.getCity());
        delegateExecution.setVariable("address", destination.getStreetAndNumber());

        logger.info("Set destination to" + destination.getCountry() + ", " + destination.getState() +
                ", " + destination.getCity() + ", " + destination.getStreetAndNumber());
    }
}
