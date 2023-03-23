package com.saha.amit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class AwsConnectionConfig {

    @Value("${aws.region}")
    public String awsRegion;

    @Bean
    public S3Client awsS3ConnectionProvider() {
        ProfileCredentialsProvider profileCredentialsProvider = ProfileCredentialsProvider.create();
        Region region = Region.of(awsRegion);
        S3Client s3Client = S3Client.builder()
                .region(region)
                .credentialsProvider(profileCredentialsProvider)
                .build();
        return s3Client;
    }

    @Bean
    public SnsClient awsSnsConnectionProvider() {
        SnsClient snsClient = SnsClient.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
        return snsClient;
    }

    @Bean
    public SqsClient awsSqsConnectionProvider() {
        SqsClient sqsClient = SqsClient.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
        return sqsClient;
    }
}
