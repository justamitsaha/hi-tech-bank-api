package com.saha.amit.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.saha.amit.config.AwsConnectionConfig;
import com.saha.amit.dto.OnboardUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class AwsSqsUtilityService {

    private static final Logger log = LoggerFactory.getLogger(AwsSqsUtilityService.class);
            @Autowired
            AwsConnectionConfig awsConnectionConfig;
            @Value("${aws.sqsUrl}")
            String sqsUrl;

            public List<OnboardUserDTO> readMessageFromQueue() {
                List<OnboardUserDTO> onboardUserDTOList = new ArrayList<>();
                try {
                    ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                            .queueUrl(sqsUrl)
                            .build();
            SqsClient sqsClient = awsConnectionConfig.awsSqsConnectionProvider();
            List<Message> messageList = sqsClient.receiveMessage(receiveMessageRequest).messages();
            if (messageList.size() > 0) {
                messageList.forEach(message ->
                        {
                            log.info(message.body());
                            String json = message.body();
                            JsonObject convertedObject = new Gson().fromJson(json, JsonObject.class);
                            log.info("Message from S3-->" + convertedObject);
                            JsonElement onBoardingJsonElement = convertedObject.get("Message");
                            if (!onBoardingJsonElement.isJsonNull()) {
                                String jsonString = String.valueOf(onBoardingJsonElement);
                                jsonString = jsonString.replace("\\", "");
                                jsonString = jsonString.substring(1, jsonString.length() - 1);
                                JsonObject jsonObject = new Gson().fromJson(jsonString, JsonObject.class);
                                OnboardUserDTO onboardUserDTO = new Gson().fromJson(jsonObject.toString(), OnboardUserDTO.class);
                                log.info("Message from queue"+ onboardUserDTO.toString());
                                onboardUserDTOList.add(onboardUserDTO);
                            } else {
                                log.info("No Valid Object found");
                            }

                        }
                );
            } else {
                log.info("No messages found");
            }
        } catch (SqsException e) {
            log.error("Error in reading message from SQS-->" + e.awsErrorDetails().errorMessage());
        }
        return onboardUserDTOList;
    }
}
