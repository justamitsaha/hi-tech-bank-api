package com.saha.amit.controller;

import com.saha.amit.dto.OnboardUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OnboardingController {

    @PostMapping("/applyToOpenAccount")
    public ResponseEntity applyToOpenAccount(@Valid @RequestBody OnboardUserDTO  onboardUserDTO){
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("rs");
    }

}
