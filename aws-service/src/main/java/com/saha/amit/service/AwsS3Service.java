package com.saha.amit.service;

import com.saha.amit.config.AwsConnectionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AwsS3Service {

    @Autowired
    AwsConnectionConfig awsConnectionConfig;

    public boolean uploadToS3( MultipartFile multipartFile , @RequestPart String fileName) throws IOException {
        byte[] bytes = multipartFile.getBytes();
        String tmpDir = System.getProperty("java.io.tmpdir");
        Path path = Paths.get(tmpDir+ fileName);
        Files.write(path, bytes);

        S3Client s3 = awsConnectionConfig.awsS3ConnectionProvider();
        String bucketName = "dev-amit-test-bucket";
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        File file = new File(tmpDir+ fileName);
        if (file.exists()){
            FileInputStream fs = new FileInputStream(file);
            var s3Response = s3.putObject(objectRequest, RequestBody.fromFile(file));
            int responseCode = s3Response.sdkHttpResponse().statusCode();
            if (responseCode == 200){
                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }
    }
}
