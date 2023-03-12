package com.saha.amit.service;

import com.saha.amit.config.OnBoardingProperties;
import com.saha.amit.dto.OnboardUserDTO;
import com.saha.amit.repository.OnboardingRepositiry;
import com.saha.amit.util.OnboardingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class OnboardingService {
    @Autowired
    OnboardingRepositiry onboardingRepositiry;
    @Autowired
    WebClient.Builder webClientBuilder;

    @Autowired
    OnBoardingProperties onBoardingProperties;

    private static final Logger log= LoggerFactory.getLogger(OnboardingService.class);

    public String applyToOpenAccount(OnboardUserDTO onboardUser, MultipartFile multipartFile1, MultipartFile multipartFile2) throws IOException {
        log.info("Start applyToOpenAccount service");
        String applicationId = OnboardingUtil.generateApplicationID();
        onboardUser.setApplicationId(applicationId);
        onboardUser.setEmailOtp(OnboardingUtil.generateRandomSixDigit());
        onboardUser.setTextOtp(OnboardingUtil.generateRandomSixDigit());
        onboardUser.setAttachment1Name(OnboardingUtil.getFileNameForStoring(multipartFile1, applicationId));
        onboardUser.setAttachment2Name(OnboardingUtil.getFileNameForStoring(multipartFile2,applicationId));

        System.out.println(onBoardingProperties.getRedisSaveApplicationEndPoint());

        try {
            ResponseEntity responseEntity =webClientBuilder.build().post()
                    .uri(onBoardingProperties.getRedisSaveApplicationEndPoint())
                    .body(Mono.just(onboardUser), OnboardUserDTO.class)
                    .retrieve()
                    .bodyToMono(ResponseEntity.class)
                    .block();
            log.info(responseEntity.getBody().toString());
        } catch (Exception e){
            log.error("Error in getting Redis cache service"+ e);
        }


//        String tmpDir = System.getProperty("java.io.tmpdir");
//        log.info("Temp file path: " + tmpDir);
//        String filePath = OnboardingUtil.saveFileLocally(tmpDir,multipartFile1,applicationId);
//        log.info("File --> "+ filePath);
        try {
            //onboardingRepositiry.save(onboardUser);
        }catch (Exception e ){
            log.error("Error adding customer" +e.toString());
            throw e;
        }
        log.info("End applyToOpenAccount service");
        return onboardUser.getApplicationId();
    }

}
