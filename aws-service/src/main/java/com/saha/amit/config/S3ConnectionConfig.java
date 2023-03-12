package com.saha.amit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3ConnectionConfig {

    @Bean
    public S3Client awsConnectionProvider(){
        ProfileCredentialsProvider profileCredentialsProvider = ProfileCredentialsProvider.create();
        Region region = Region.AP_SOUTH_1;
        S3Client s3Client = S3Client.builder()
                .region(region)
                .credentialsProvider(profileCredentialsProvider)
                .build();
        return s3Client;
    }
}
