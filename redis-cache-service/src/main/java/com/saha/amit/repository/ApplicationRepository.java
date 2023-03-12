package com.saha.amit.repository;

import com.saha.amit.entity.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApplicationRepository {

    public static final String HASH_KEY = "Application";
    @Autowired
    private RedisTemplate redisTemplate;
    private final static Logger log = LoggerFactory.getLogger(ApplicationRepository.class);

    public Application saveApplication(Application application){
        try {
            redisTemplate.opsForHash().put(HASH_KEY, application.getApplicationId(),application);
        } catch (Exception e){
            log.error(e.getStackTrace().toString());
            throw  e;
        }

        return application;
    }

    public List<Application> getAllApplication(){
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public Application findApplicationById(String applicationId){
        //Devtool dependency was causing issue in casting so had to be removed
        return (Application) redisTemplate.opsForHash().get(HASH_KEY,applicationId);
    }

    public String deleteApplication(String applicationId){
        redisTemplate.opsForHash().delete(HASH_KEY,applicationId);
        return "product removed !!";
    }

}
