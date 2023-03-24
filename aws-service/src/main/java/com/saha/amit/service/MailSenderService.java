package com.saha.amit.service;

import com.saha.amit.config.MailConfig;
import com.saha.amit.dto.OtpMailSenderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    private static final Logger log = LoggerFactory.getLogger(MailSenderService.class);
    @Autowired
    private JavaMailSender emailSender;
    @Value("${spring.mail.username}")
    String mailId;

    String subject;
    @Value("mail.otp.body")
    String body;
    public boolean sendMail(Object object){
        if(object instanceof OtpMailSenderDTO){
            OtpMailSenderDTO otpMailSenderDTO = (OtpMailSenderDTO) object;
            log.info("Sending mail to -->"+(otpMailSenderDTO.getEmail()));
            SimpleMailMessage message = new SimpleMailMessage();
            //subject = subject.replace("{#OTP}",otpMailSenderDTO.getOtp());
            subject = "Your OTP is "+ otpMailSenderDTO.getOtp();
            message.setFrom(mailId);
            message.setTo(otpMailSenderDTO.getEmail());
            message.setSubject(subject);
            message.setText(body);
            emailSender.send(message);
        }
        return true;
    }
}
