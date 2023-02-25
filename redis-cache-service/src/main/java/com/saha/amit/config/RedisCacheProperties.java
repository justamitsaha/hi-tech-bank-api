package com.saha.amit.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@RefreshScope
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisCacheProperties {
    //Below props are used in bean definition so even if they change won't reflect
    @Value("${redis.host}")
    private String redisHostName;
    @Value("${redis.port}")
    private int redisPort;


    //Below are some test properties which can be changed using Actuator endpoints
    @Value("${sample.textObj}")
    private String textObj;

    @Value("${sample.listObj}")
    public List<String> listObj;

    @Value("#{${sample.mapObj}}")
    public Map<String,String> mapObj;

}
