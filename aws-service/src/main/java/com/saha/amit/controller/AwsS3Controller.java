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

    @GetMapping("public/test")
    public String test(){

        return "<!DOCTYPE html><html><head><script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script></head><body><div><form method=\"post\" " +
                "action=\"\" enctype=\"multipart/form-data\" id=\"myform\"><div><input type=\"file\" id=\"file\" name=\"file\"> <input type=\"text\" id=\"fileName\" " +
                "name=\"text\"> <input type=\"button\" class=\"button\" value=\"Upload\" id=\"but_upload\"></div></form></div><script type=\"text/javascript\">" +
                "$(document).ready(function(){$(\"#but_upload\").click(function(){var e=new FormData,a=$(\"#file\")[0].files[0],l=$(\"#fileName\").val();" +
                "e.append(\"multipartFile\",a),e.append(\"fileName\",l),$.ajax({url:\"http://localhost:8080/awsS3Service/public/uploadToS3\",type:\"post\",data:e," +
                "contentType:!1,processData:!1,success:function(e){0!=e?alert(\"file uploaded\"):alert(\"file not uploaded\")}})})})</script></body></html>";
    }
}
