package com.saha.amit.service;

import com.saha.amit.AwsServiceApplication;
import com.saha.amit.config.MailConfig;
import com.saha.amit.dto.OtpMailSenderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailSenderService {
    private static final Logger log = LoggerFactory.getLogger(MailSenderService.class);
    @Autowired
    private JavaMailSender emailSender;
    @Value("${spring.mail.username}")
    String mailId;

    @Value("${mail.otp.subject}")
    String subject;
    String body;
    public boolean sendMail(Object object) throws MessagingException {
        if(object instanceof OtpMailSenderDTO){
            OtpMailSenderDTO otpMailSenderDTO = (OtpMailSenderDTO) object;
            log.info("Sending mail to -->"+(otpMailSenderDTO.getEmail()));
            body = AwsServiceApplication.EMAIL_OTP_TEMPLATE;
            body = body.replace("{OTP}",otpMailSenderDTO.getOtp());
            String subjectMail = otpMailSenderDTO.getType() + subject;
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setSubject(subjectMail);
            mimeMessageHelper.setText(body,true);
            mimeMessageHelper.setTo(otpMailSenderDTO.getEmail());
            mimeMessageHelper.setFrom(mailId);
            emailSender.send(mimeMessage);
        }
        return true;
    }
}
