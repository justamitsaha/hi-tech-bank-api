package com.saha.amit.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AwsS3Controller {

    @Value("${aws}")
    private String test;

    @GetMapping("public/test")
    public String test(){
        return test;
    }
}
