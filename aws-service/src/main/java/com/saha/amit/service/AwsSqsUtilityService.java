package com.saha.amit.service;

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
            messageList.forEach(message ->
                    logger.info(message.body())
            );
        } catch (SqsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
