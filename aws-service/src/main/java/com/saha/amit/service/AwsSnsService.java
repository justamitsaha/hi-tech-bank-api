package com.saha.amit.service;

import com.google.gson.Gson;
import com.saha.amit.config.AwsConnectionConfig;
import com.saha.amit.dto.OnboardUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

@Service
public class AwsSnsService {

    @Autowired
    AwsConnectionConfig awsConnectionConfig;
    public boolean publishToQueue(OnboardUserDTO onboardUserDTO){
        SnsClient snsClient = awsConnectionConfig.awsSnsConnectionProvider();

        var data = new Gson().toJson(onboardUserDTO);
        try {
            PublishRequest request = PublishRequest.builder()
                    .message(data)
                    .topicArn("arn:aws:sns:ap-south-1:615839970612:on-boarding")
                    .build();

            PublishResponse result = snsClient.publish(request);
            System.out.println(result.messageId() + " Message sent. Status is " + result.sdkHttpResponse().statusCode());

        } catch (SnsException e) {
            System.err.println("Error 1"+e.awsErrorDetails().errorMessage());
        }
        return true;
    }
}