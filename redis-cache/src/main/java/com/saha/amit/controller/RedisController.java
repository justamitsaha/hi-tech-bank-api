package com.saha.amit.controller;

import com.saha.amit.entity.Application;
import com.saha.amit.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RedisController {

    @Autowired
    ApplicationRepository applicationRepository;

    @PostMapping("public/saveApplication")
    public Application saveApplication(@RequestBody Application application){
       return applicationRepository.saveApplication(application);
    }

    @GetMapping("public/getAllApplication")
    public List<Application> getAllApplication(){
        return applicationRepository.getAllApplication();
    }

    @GetMapping("public/findApplicationById/{applicationId}")
    public Application findApplicationById(@PathVariable String applicationId){
        return applicationRepository.findApplicationById(applicationId);
    }

    @DeleteMapping("public/deleteApplication/{applicationId}")
    public String deleteApplication(String applicationId){
        return applicationRepository.deleteApplication(applicationId);
    }
}
