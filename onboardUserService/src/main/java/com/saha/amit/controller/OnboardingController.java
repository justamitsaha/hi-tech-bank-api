package com.saha.amit.controller;

import com.saha.amit.model.OnboardUser;
import com.saha.amit.service.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OnboardingController {

    @Autowired
    OnboardingService onboardingService;

    @PostMapping("/applyToOpenAccount")
    public ResponseEntity saveCustomerApplication(@Valid @RequestBody OnboardUser onboardUser) {
        var response = onboardingService.saveCustomerApplication(onboardUser);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

}
