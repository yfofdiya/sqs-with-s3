package com.simform.service;

import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SendResult;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SQSService {

    private final Logger logger = LoggerFactory.getLogger(SQSService.class);

    @Autowired
    private SqsTemplate sqsTemplate;

    @Value("${aws.sqs.name}")
    private String queue;

    public String sendMessageToSqsQueue(String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", message);
        String stringMessage = jsonObject.toString();
        SendResult<Object> send = sqsTemplate.send(sqsSendOptions -> sqsSendOptions.queue(queue).delaySeconds(60).payload(stringMessage));
        logger.info("Object is {}", send);
        logger.info(message + " sent to SQS Queue on " + new Date());
        return "Message sent";
    }
}
