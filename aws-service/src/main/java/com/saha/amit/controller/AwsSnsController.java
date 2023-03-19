package com.saha.amit.controller;

import com.saha.amit.dto.OnboardUserDTO;
import com.saha.amit.service.AwsSnsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AwsSnsController {

    @Autowired
    AwsSnsService awsSnsService;
    private static final Logger log = LoggerFactory.getLogger(AwsSnsController.class);
    @PutMapping("public/publishToSNS")
    public Boolean publishToSNS(@RequestBody OnboardUserDTO onboardUserDTO) {
        log.info("Inside publishToQueue");
        return awsSnsService.publishToQueue(onboardUserDTO);
    }

}
