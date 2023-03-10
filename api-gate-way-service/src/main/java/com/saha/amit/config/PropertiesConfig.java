package com.saha.amit.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RefreshScope
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertiesConfig {
    @Value("${allowedUrls}")
    private List<String> allowedUrls;
    @Value("${jwtKey}")
    private String jwtKey;
}
