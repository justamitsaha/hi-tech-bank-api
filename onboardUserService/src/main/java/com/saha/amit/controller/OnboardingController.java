package com.saha.amit.controller;

import com.saha.amit.model.OnboardUser;
import com.saha.amit.service.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.logging.Logger;

@RestController
public class OnboardingController {

    @Autowired
    OnboardingService onboardingService;
    private final Logger log = Logger.getLogger(OnboardingController.class.getName());

    @PostMapping(value = "/public/applyToOpenAccount", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity saveCustomerApplication(@RequestPart OnboardUser onboardUser, @RequestPart MultipartFile multipartFile1,  @RequestPart MultipartFile multipartFile2) throws IOException {
        var response = onboardingService.saveCustomerApplication(onboardUser, multipartFile1, multipartFile2);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
