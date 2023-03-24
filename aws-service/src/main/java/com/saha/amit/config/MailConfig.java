package com.saha.amit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    @Value("${spring.mail.host}")
    String host;
    @Value("${spring.mail.port}")
    int port;
    @Value("${spring.mail.username}")
    String mailId;
    @Value("${spring.mail.password}")
    String cred;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    String auth;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    String starttls;
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        System.out.println(host+ port+mailId+cred);
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(mailId);
        mailSender.setPassword(cred);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.debug", "true");

        return mailSender;
    }
}
