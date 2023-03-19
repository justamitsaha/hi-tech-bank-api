package com.saha.amit.controller;

import com.saha.amit.service.AwsSqsUtilityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AwsSqsController {
    @Autowired
    AwsSqsUtilityService awsSqsUtilityService;
    private static final Logger log = LoggerFactory.getLogger(AwsSqsController.class);
    @GetMapping("public/readMessageFromQueue")
    public boolean readMessageFromQueue() {
        log.info("Inside readMessageFromQueue");
        awsSqsUtilityService.readMessageFromQueue();
        return true;
    }
}
