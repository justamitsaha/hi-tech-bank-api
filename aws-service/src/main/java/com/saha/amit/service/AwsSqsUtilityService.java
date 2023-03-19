package com.saha.amit.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.saha.amit.config.AwsConnectionConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.List;

@Service
public class AwsSqsUtilityService {

    private static final Logger logger = LoggerFactory.getLogger(AwsSqsUtilityService.class);
    @Autowired
    AwsConnectionConfig awsConnectionConfig;

    public void readMessageFromQueue() {
        try {
            ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                    .queueUrl("https://sqs.ap-south-1.amazonaws.com/615839970612/on-boarding-queue")
                    .maxNumberOfMessages(5)
                    .build();
            SqsClient sqsClient = awsConnectionConfig.awsSqsConnectionProvider();
            List<Message> messageList = sqsClient.receiveMessage(receiveMessageRequest).messages();
            if (messageList.size() > 0) {
                messageList.forEach(message ->
                        {
                            logger.info(message.body());
                            String json = message.body();
                            JsonObject convertedObject = new Gson().fromJson(json, JsonObject.class);
                            logger.info("Message from S3-->" + convertedObject);
                        }
                );
            } else {
                logger.info("No messages found");
            }
        } catch (SqsException e) {
            logger.error("Error in reading message from SQS-->" + e.awsErrorDetails().errorMessage());
        }
    }
}
