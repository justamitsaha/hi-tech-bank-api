package com.saha.amit.service;

import com.saha.amit.model.OnboardUser;
import com.saha.amit.repository.OnboardingRepositiry;
import com.saha.amit.util.OnboardingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

@Service
public class OnboardingService {
    @Autowired
    OnboardingRepositiry onboardingRepositiry;
    private final Logger log = Logger.getLogger(OnboardingService.class.getName());
    public String saveCustomerApplication(OnboardUser onboardUser, MultipartFile multipartFile1, MultipartFile multipartFile2) throws IOException {
        String applicationId = OnboardingUtil.generateApplicationID();
        onboardUser.setApplicationId(applicationId);

        String tmpDir = System.getProperty("java.io.tmpdir");
        log.info("Temp file path: " + tmpDir);
        String filePath = OnboardingUtil.saveFileLocally(tmpDir,multipartFile1,applicationId);
        log.info("File --> "+ filePath);
        try {
            //onboardingRepositiry.save(onboardUser);
        }catch (Exception e ){
            log.info("Error adding customer" +e.toString());
            throw e;
        }
        return onboardUser.getApplicationId();
    }

}
