package com.saha.amit.controller;

import com.saha.amit.dto.OnboardUserDTO;
import com.saha.amit.service.AwsSnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AwsSnsController {

    @Autowired
    AwsSnsService awsSnsService;

    @PutMapping("public/publishToQueue")
    public Boolean publishToQueue(@RequestBody OnboardUserDTO onboardUserDTO){
        return awsSnsService.publishToQueue(onboardUserDTO);
    }
    
}
