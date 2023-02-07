package com.saha.amit.controller;

import com.saha.amit.entity.Application;
import com.saha.amit.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RedisController {

    @Autowired
    ApplicationRepository applicationRepository;

    @PostMapping("public/saveApplication")
    public ResponseEntity<Application> saveApplication(@RequestBody Application application){
       return ResponseEntity.status(HttpStatus.ACCEPTED).body(applicationRepository.saveApplication(application));
    }

    @GetMapping("public/getAllApplication")
    public ResponseEntity<List<Application>>getAllApplication(){
            return ResponseEntity.status(HttpStatus.OK).body(applicationRepository.getAllApplication());
    }

    @GetMapping("public/findApplicationById/{applicationId}")
    public ResponseEntity<Application> findApplicationById(@PathVariable String applicationId){
        return ResponseEntity.status(HttpStatus.OK).body(applicationRepository.findApplicationById(applicationId));
    }

    @DeleteMapping("public/deleteApplicationById/{applicationId}")
    public ResponseEntity<String> deleteApplication(@PathVariable String applicationId){
        return ResponseEntity.status(HttpStatus.OK).body(applicationRepository.deleteApplication(applicationId));
    }
}
