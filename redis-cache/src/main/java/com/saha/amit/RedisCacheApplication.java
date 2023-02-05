package com.saha.amit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RedisCacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisCacheApplication.class,args);
    }
}
