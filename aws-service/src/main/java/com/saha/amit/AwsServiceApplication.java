package com.saha.amit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
public class AwsServiceApplication {
    public static String EMAIL_OTP_TEMPLATE = "";

    public static void main(String[] args) throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:otpTemplate/RegisterationOTP.html");
        try (Scanner scanner = new Scanner(file);) {
            while(scanner.hasNextLine()) {
                EMAIL_OTP_TEMPLATE += scanner.nextLine();
            }
        }
        System.out.println(EMAIL_OTP_TEMPLATE);
        SpringApplication.run(AwsServiceApplication.class, args);
    }
}
