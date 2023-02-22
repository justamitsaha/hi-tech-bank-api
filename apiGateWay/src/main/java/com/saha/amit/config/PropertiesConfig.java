package com.saha.amit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PropertiesConfig {

    @Value("${allowedUrls}")
    public static List<String> ALLOWED_URLS;
    @Value("${jwtKey}")
    public static String JWT_KEY;
}
