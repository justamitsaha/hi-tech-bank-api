package com.saha.amit.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnBoardingProperties {

    @Value("${redis-app.saveApplication}")
    private String redisSaveApplicationEndPoint;

    @Value("${aws.fileUpload}")
    private String awsS3UploadEndpoint;

    @Value("${aws.postSNS}")
    private String awsSNSPost;

    @Value("${redis-app.getApplication}")
    private String redisGetApplication;
}
