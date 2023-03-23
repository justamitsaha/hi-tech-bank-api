package com.saha.amit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.saha.amit.config.AwsConnectionConfig;
import com.saha.amit.dto.OnboardUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

import java.io.IOException;

@Service
public class AwsSnsService {
    private static final Logger logger = LoggerFactory.getLogger(AwsSnsService.class);
    @Autowired
    AwsConnectionConfig awsConnectionConfig;
    @Value("${aws.snsArn}")
    String snsArn;

    public boolean publishToQueue(OnboardUserDTO onboardUserDTO) {
        try  {
            var data = new Gson().toJson(onboardUserDTO);
            PublishRequest request = PublishRequest.builder()
                    .message(data)
                    .topicArn(snsArn)
                    .build();
            SnsClient snsClient = awsConnectionConfig.awsSnsConnectionProvider();
            PublishResponse result = snsClient.publish(request);
            logger.info(result.messageId() + " Message sent. Status is " + result.sdkHttpResponse().statusCode());

        } catch (SnsException e) {
            logger.error("Error SnsException" + e.awsErrorDetails().errorMessage());
        } catch (Exception e){
            logger.error("Error " + e);
        }
        return true;
    }
}
