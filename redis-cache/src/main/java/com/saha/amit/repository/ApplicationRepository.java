package com.saha.amit.repository;

import com.saha.amit.entity.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApplicationRepository {

    public static final String HASH_KEY = "Application";
    @Autowired
    private RedisTemplate redisTemplate;

    public Application saveApplication(Application application){
        redisTemplate.opsForHash().put(HASH_KEY, application.getApplicationId(),application);
        return application;
    }

    public List<Application> getAllApplication(){
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public Application findApplicationById(String applicationId){
        return (Application) redisTemplate.opsForHash().get(HASH_KEY,applicationId);
    }

    public String deleteApplication(String applicationId){
        redisTemplate.opsForHash().delete(HASH_KEY,applicationId);
        return "product removed !!";
    }

}
