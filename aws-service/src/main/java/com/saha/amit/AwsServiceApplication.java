package com.saha.amit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
public class AwsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AwsServiceApplication.class,args);
    }
}
