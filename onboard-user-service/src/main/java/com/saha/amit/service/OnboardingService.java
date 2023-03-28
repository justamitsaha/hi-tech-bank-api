package com.saha.amit.service;

import com.saha.amit.config.OnBoardingProperties;
import com.saha.amit.constants.OnboardingConstants;
import com.saha.amit.dto.OnboardUserDTO;
import com.saha.amit.dto.ValidateOtpDTO;
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
import java.util.LinkedHashMap;
import java.util.List;

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
        var fileUploadPayload = builder.build();

        String fileUploadResponse = "fileUploadResponse";
        String redisUpdateResponse = "redisUpdateResponse";
        String postSQSResponse = "postSQSResponse";

        List<Object> responseList = List.of(fileUploadResponse, redisUpdateResponse, postSQSResponse);
        Date startDate = new Date();
        log.info("Transaction started at " + startDate);
        responseList.stream().map(s -> {
            if (s.equals("fileUploadResponse")) {
                s = makeApiCall(onBoardingProperties.getAwsS3UploadEndpoint(), true, Object.class, fileUploadPayload, OnboardingConstants.POST);
            } else if (s.equals("redisUpdateResponse")) {
                s = makeApiCall(onBoardingProperties.getRedisSaveApplicationEndPoint(), false, OnboardUserDTO.class, onboardUser, OnboardingConstants.POST);
            } else if (s.equals("postSQSResponse")) {
                s = makeApiCall(onBoardingProperties.getAwsSNSPost(), false, Boolean.class, onboardUser, OnboardingConstants.PUT);
            }
            return s;
        }).forEach(s -> {
            log.info("Response Stream " + s.toString());
        });

        Date endDate = new Date();
        log.info("Transaction ended at " + endDate);
        log.info("Time taken for Transaction " + (endDate.getTime() - startDate.getTime()) / 1000);


        try {
            //onboardingRepositiry.save(onboardUser);
        } catch (Exception e) {
            log.error("Error adding customer" + e.toString());
            throw e;
        }
        log.info("End applyToOpenAccount service");
        return onboardUser.getApplicationId();
    }

    public boolean validateOtp(ValidateOtpDTO validateOtpDTO) {
        log.info("Inside Validate OTP service for " + validateOtpDTO.getType());
        var response =(LinkedHashMap)makeApiCall(onBoardingProperties.getRedisGetApplication(), false, OnboardUserDTO.class, validateOtpDTO.getApplicationId(), OnboardingConstants.GET);
        var otp = (validateOtpDTO.getType().equals(OnboardingConstants.SMS_TYPE_OTP)) ? response.get("textOtp") : response.get("emailOtp");
        return validateOtpDTO.getOtp().equals(otp);
    }

    public Object makeApiCall(String url, Boolean isFormPost, Class<?> requestClass, Object payload, String methodType) {
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
            } catch (Exception e) {
                log.error("Error in " + url + " API call" + e);
            }
        } else {
            if (methodType.equals(OnboardingConstants.POST)) {
                try {
                    response = webClientBuilder.build().post()
                            .uri(url)
                            .body(Mono.just(payload), requestClass)
                            .retrieve()
                            .bodyToMono(Object.class)
                            .block();
                } catch (Exception e) {
                    log.error("Error in " + url + " API call" + e);
                }
            } else if (methodType.equals(OnboardingConstants.PUT)) {
                try {
                    response = webClientBuilder.build().put()
                            .uri(url)
                            .body(Mono.just(payload), requestClass)
                            .retrieve()
                            .bodyToMono(Object.class)
                            .block();
                } catch (Exception e) {
                    log.error("Error in " + url + " API call" + e);
                }
            } else if (methodType.equals(OnboardingConstants.GET)) {
                try {
                    response = webClientBuilder.build().get()
                            .uri(url,
                                    uriBuilder -> uriBuilder.queryParam("applicationId", payload.toString()).build())
                            .retrieve()
                            .bodyToMono(Object.class)
                            .block();
                    assert response != null;
                    log.info(response.toString());
                } catch (Exception e) {
                    log.error("Error in " + url + " API call" + e);
                }
            }


        }
        Date endDate = new Date();
        log.info("Api call for --> " + url + " ended at " + endDate);
        log.info("Time taken for Api call " + (endDate.getTime() - startDate.getTime()) / 1000);
        return response;
    }
}
