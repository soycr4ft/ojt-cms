package com.ojt.cms.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class MailConfig {

    // SMTP 서버
    @Value("${spring.mail.host}")
    private String host;
    // 계정
    @Value("${spring.mail.username}")
    private String username;
    // 비밀번호
    @Value("${spring.mail.password}")
    private String password;
    // 포트번호
    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean auth;
    @Value("${spring.mail.properties.mail.smtp.debug}")
    private boolean debug;
    @Value("${spring.mail.properties.mail.smtp.connectiontimeout}")
    private int connectionTimeout;
    @Value("${spring.mail.properties.mail.starttls.enable}")
    private boolean startTlsEnable;
    
    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        javaMailSender.setPort(port);

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", startTlsEnable);
        props.put("mail.smtp.connectiontimeout", connectionTimeout);
        props.put("mail.smtp.debug", debug);

        javaMailSender.setJavaMailProperties(props);
        javaMailSender.setDefaultEncoding("UTF-8");

        return javaMailSender;
    }
}
