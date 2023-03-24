package com.saha.amit.controller;

import com.saha.amit.dto.OnboardUserDTO;
import com.saha.amit.dto.OtpMailSenderDTO;
import com.saha.amit.service.AwsSqsUtilityService;
import com.saha.amit.service.MailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AwsSqsController {
    private static final Logger log = LoggerFactory.getLogger(AwsSqsController.class);
    @Autowired
    AwsSqsUtilityService awsSqsUtilityService;
    @Autowired
    MailSenderService mailSenderService;

    @GetMapping("public/readMessageFromQueue")
    public boolean readMessageFromQueue() {
        log.info("Inside readMessageFromQueue");
        List<OnboardUserDTO> onboardUserDTOList = awsSqsUtilityService.readMessageFromQueue();
        List<OtpMailSenderDTO> otpMailSenderDTOList = new ArrayList<>();
        onboardUserDTOList.forEach(onboardUserDTO -> {
            OtpMailSenderDTO otpMailSenderDTO = new OtpMailSenderDTO();
            OtpMailSenderDTO otpMailSenderDTO1 = new OtpMailSenderDTO();
            otpMailSenderDTO.setEmail(onboardUserDTO.getEmail());
            otpMailSenderDTO.setOtp(onboardUserDTO.getEmailOtp());
            otpMailSenderDTO.setType("Email");
            otpMailSenderDTO1.setEmail(onboardUserDTO.getEmail());
            otpMailSenderDTO1.setOtp(onboardUserDTO.getTextOtp());
            otpMailSenderDTO1.setType("SMS");
            otpMailSenderDTOList.add(otpMailSenderDTO);
            otpMailSenderDTOList.add(otpMailSenderDTO1);
        });

        otpMailSenderDTOList.stream().map(otpMailSenderDTO -> {
            try {
                return mailSenderService.sendMail(otpMailSenderDTO);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }).forEach(s -> {
            log.info("Mail send status for " + otpMailSenderDTOList + " -->" + s);
        });
        return true;
    }
}
