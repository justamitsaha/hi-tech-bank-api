package com.saha.amit.controller;


import com.saha.amit.service.AwsS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class AwsS3Controller {

    @Autowired
    AwsS3Service awsS3Service;

    @PostMapping("public/uploadToS3")
    public ResponseEntity<Boolean> uploadToS3(@RequestPart MultipartFile multipartFile , @RequestPart String fileName) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(awsS3Service.uploadToS3(multipartFile,fileName));
    }

}
