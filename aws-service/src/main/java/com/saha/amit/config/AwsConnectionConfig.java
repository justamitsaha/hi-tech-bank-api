package com.saha.amit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class AwsConnectionConfig {

    @Bean
    public S3Client awsS3ConnectionProvider(){
        ProfileCredentialsProvider profileCredentialsProvider = ProfileCredentialsProvider.create();
        Region region = Region.AP_SOUTH_1;
        S3Client s3Client = S3Client.builder()
                .region(region)
                .credentialsProvider(profileCredentialsProvider)
                .build();
        return s3Client;
    }
    @Bean
    public SnsClient awsSnsConnectionProvider(){
        SnsClient snsClient = SnsClient.builder()
                .region(Region.AP_SOUTH_1)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
        return snsClient;
    }
}
