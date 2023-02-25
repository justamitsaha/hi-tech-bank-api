package com.saha.amit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EnableEurekaClient
@ComponentScans({ @ComponentScan("com.saha.amit")})
public class ApiGateWayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGateWayServiceApplication.class,args);
    }
}
