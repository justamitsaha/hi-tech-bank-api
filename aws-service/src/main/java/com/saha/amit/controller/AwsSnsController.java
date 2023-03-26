package com.saha.amit.controller;

import com.saha.amit.dto.OnboardUserDTO;
import com.saha.amit.service.AwsSnsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AwsSnsController {

    @Autowired
    AwsSnsService awsSnsService;
    private static final Logger log = LoggerFactory.getLogger(AwsSnsController.class);
    @PutMapping("public/publishToSNS")
    public ResponseEntity<Boolean> publishToSNS(@RequestBody OnboardUserDTO onboardUserDTO) {
        log.info("Inside publishToQueue");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(awsSnsService.publishToQueue(onboardUserDTO));
    }

}
