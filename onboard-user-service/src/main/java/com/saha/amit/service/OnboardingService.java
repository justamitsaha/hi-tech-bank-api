package com.saha.amit.service;

import com.saha.amit.config.OnBoardingProperties;
import com.saha.amit.dto.OnboardUserDTO;
import com.saha.amit.repository.OnboardingRepositiry;
import com.saha.amit.util.OnboardingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    private static final Logger log = LoggerFactory.getLogger(OnboardingService.class);
    @Autowired
    OnboardingRepositiry onboardingRepositiry;
    @Autowired
    WebClient.Builder webClientBuilder;
    @Autowired
    OnBoardingProperties onBoardingProperties;

    public String applyToOpenAccount(OnboardUserDTO onboardUser, MultipartFile multipartFile1, MultipartFile multipartFile2) throws IOException {
        log.info("Start applyToOpenAccount service");
        String applicationId = OnboardingUtil.generateApplicationID();
        onboardUser.setApplicationId(applicationId);
        onboardUser.setEmailOtp(OnboardingUtil.generateRandomSixDigit());
        onboardUser.setTextOtp(OnboardingUtil.generateRandomSixDigit());
        onboardUser.setAttachment1Name(OnboardingUtil.getFileNameForStoring(multipartFile1, applicationId));
        onboardUser.setAttachment2Name(OnboardingUtil.getFileNameForStoring(multipartFile2, applicationId));

        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("multipartFile", multipartFile1);
        formData.add("password", onboardUser.getAttachment1Name());

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("multipartFile", multipartFile1.getBytes())
                .header("Content-Disposition", "form-data; name=multipartFile; filename=profile-image.jpg");
        builder.part("fileName", onboardUser.getAttachment1Name(), MediaType.TEXT_PLAIN)
                .header("Content-Disposition", "form-data; name=fileName").header("Content-type", "text/plain");
        var fileUploadPayload =builder.build();

        makeApiCall(onBoardingProperties.getRedisSaveApplicationEndPoint(), false, OnboardUserDTO.class, onboardUser);
        makeApiCall(onBoardingProperties.getAwsS3UploadEndpoint(), true, Object.class, fileUploadPayload);


        try {
            //onboardingRepositiry.save(onboardUser);
        } catch (Exception e) {
            log.error("Error adding customer" + e.toString());
            throw e;
        }
        log.info("End applyToOpenAccount service");



        return onboardUser.getApplicationId();
    }

    public Object makeApiCall(String url, Boolean isFormPost, Class<?> requestClass, Object payload) {
        Date startDate = new Date();
        log.info("Api call for --> " + url + " started at " + startDate);
        Object response = null;
        if (isFormPost) {
            try {
                response = webClientBuilder.build().post()
                        .uri(url)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .bodyValue(payload)
                        .retrieve()
                        .bodyToMono(Object.class)
                        .block();
                log.info(response.toString());
            } catch (Exception e) {
                log.error("Error in getting Redis cache service" + e);
            }
        } else {
            response = webClientBuilder.build().post()
                    .uri(url)
                    .body(Mono.just(payload), requestClass)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
            log.info("For API  " + response.toString());
        }
        Date endDate = new Date();
        log.info("Api call for --> " + url + " ended at " + endDate);
        log.info("Time taken for Api call " + (endDate.getTime() - startDate.getTime()) / 1000);
        return response;
    }

}
