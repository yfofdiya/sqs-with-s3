package com.simform.controller;

import com.simform.service.SQSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SQSController {

    @Autowired
    private SQSService sqsService;

    @GetMapping("/send")
    public String sendMessage(@RequestBody String message) {
        return sqsService.sendMessageToSqsQueue(message);
    }
}