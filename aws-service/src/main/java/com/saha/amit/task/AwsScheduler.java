package com.saha.amit.task;

import com.saha.amit.service.AwsSqsUtilityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class AwsScheduler {
    private static final Logger logger = LoggerFactory.getLogger(AwsScheduler.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    @Autowired
    AwsSqsUtilityService awsSqsUtilityService;

    @Scheduled(fixedRate = 15 * 60 * 1000)
    public void scheduleTaskWithFixedRate() {
        awsSqsUtilityService.readMessageFromQueue();
    }

    public void scheduleTaskWithFixedDelay() {
    }

    public void scheduleTaskWithInitialDelay() {
    }

    public void scheduleTaskWithCronExpression() {
    }
}
