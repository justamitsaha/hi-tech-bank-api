package com.saha.amit.controller;

import com.saha.amit.dto.OnboardUserDTO;
import com.saha.amit.dto.ValidateOtpDTO;
import com.saha.amit.service.OnboardingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;


@RestController
@RefreshScope
public class OnboardingController {

    @Value("${my.greeting: default lau}")
    private String config;

    @Value("${my.list.values}")
    private List<String> listStrings;

    @Value("${demo.property: noValue}")
    private String testConfig;

    @Autowired
    OnboardingService onboardingService;

    private static final Logger log= LoggerFactory.getLogger(OnboardingController.class);

    @PostMapping(value = "/public/applyToOpenAccount", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> applyToOpenAccount(@RequestPart OnboardUserDTO onboardUser, @RequestPart MultipartFile multipartFile1, @RequestPart MultipartFile multipartFile2) throws IOException {
        log.info("Start applyToOpenAccount controller");
        var response = onboardingService.applyToOpenAccount(onboardUser, multipartFile1, multipartFile2);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @GetMapping(value = "/public/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(config +"<-->"+ listStrings.toString()+"<-->"+testConfig);
    }

    @PutMapping(value = "/public/validateOtp")
    public ResponseEntity<Boolean> validateOTP(@RequestBody ValidateOtpDTO validateOtpDTO){
        log.info("Start validateOTP controller for "+ validateOtpDTO.getType());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(onboardingService.validateOtp(validateOtpDTO));
    }
}
