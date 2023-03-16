package com.saha.amit.service;

import com.saha.amit.config.OnBoardingProperties;
import com.saha.amit.dto.OnboardUserDTO;
import com.saha.amit.repository.OnboardingRepositiry;
import com.saha.amit.util.OnboardingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Date;

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
        Object awsResponse= null;
        Object redisResponse= null;
        Date date = new Date();
        log.info("Redis cache API call start"+ date);
        try {
            redisResponse = webClientBuilder.build().post()
                    .uri(onBoardingProperties.getRedisSaveApplicationEndPoint())
                    .body(Mono.just(onboardUser), OnboardUserDTO.class)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
            log.info("Redis Response "+redisResponse.toString());
        } catch (Exception e){
            log.error("Error in getting Redis cache service"+ e);
        }

        Date date1 = new Date();
        log.info("Time taken for 1st API call --> "+ (date1.getTime()- date.getTime())/1000);

        log.info("AWS API call start"+ date1);

        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("multipartFile", multipartFile1);
        formData.add("password", onboardUser.getAttachment1Name());

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("multipartFile", multipartFile1.getBytes())
                .header("Content-Disposition", "form-data; name=multipartFile; filename=profile-image.jpg");
        builder.part("fileName", onboardUser.getAttachment1Name(), MediaType.TEXT_PLAIN)
                .header("Content-Disposition", "form-data; name=fileName").header("Content-type", "text/plain");

        try {
            awsResponse =webClientBuilder.build().post()
                    .uri(onBoardingProperties.getAwsS3UploadEndpoint())
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .bodyValue(builder.build())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
            log.info(awsResponse.toString());
        } catch (Exception e){
            log.error("Error in getting Redis cache service"+ e);
        }
        Date date2 = new Date();
        log.info("AWS API call end "+date2);
        log.info("AWS API call time taken "+ (date2.getTime()- date1.getTime())/1000);
        log.info("Total time taken "+(date2.getTime()- date1.getTime())/1000);

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
