package com.tista.com;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;
import java.util.logging.Logger;

public class RunJenkinsJob implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger("JENKINS-LOGGER");

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String jobName = (String) delegateExecution.getVariable("jenkinsJobName");
        LOGGER.info(jobName);

        TriggerJenkins triggerJenkins = new TriggerJenkins();
        Map<String, String> map  = triggerJenkins.triggerJenkinsBuild(jobName);

        for (Map.Entry<String,String> entry : map.entrySet()){
            LOGGER.info("Key = " + entry.getKey());
            LOGGER.info( "Value = " + entry.getValue());
        }
    }
}
