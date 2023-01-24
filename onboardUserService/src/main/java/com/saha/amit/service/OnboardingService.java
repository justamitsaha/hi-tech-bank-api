package com.saha.amit.service;

import com.saha.amit.model.OnboardUser;
import com.saha.amit.repository.OnboardingRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Logger;

@Service
public class OnboardingService {
    @Autowired
    OnboardingRepositiry onboardingRepositiry;
    private final Logger log = Logger.getLogger(OnboardingService.class.getName());
    public String saveCustomerApplication(OnboardUser onboardUser){
        var date = new Date();
        String secs = String.valueOf((date.getTime())/1000);
        int min = 10000;
        int max = 99999;
        String applicationID = secs +"-"+(int)Math.floor(Math.random() * (max - min + 1) + min);
        onboardUser.setApplicationId(applicationID);
        onboardUser.setSubmittedOn(date);
        log.info("Application id"+ applicationID +" - "+ onboardUser.getApplicationId());
        try {
            onboardingRepositiry.save(onboardUser);
        }catch (Exception e ){
            log.info("Error adding customer" +e.toString());
            throw e;
        }

        return applicationID;
    }

}
