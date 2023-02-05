package com.saha.amit.controller;

import com.saha.amit.model.OnboardUser;
import com.saha.amit.service.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
public class OnboardingController {

    @Autowired
    OnboardingService onboardingService;

    @PostMapping(value = "/public/applyToOpenAccount", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity saveCustomerApplication(@RequestPart OnboardUser onboardUser, @RequestPart MultipartFile multipartFile1,  @RequestPart MultipartFile multipartFile2) {
        var response = onboardingService.saveCustomerApplication(onboardUser);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
