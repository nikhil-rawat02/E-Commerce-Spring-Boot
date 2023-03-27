package com.Ecommers.shopping.Communication;


import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfiguration {

//    @Value("${spring.mail.host}")
//    private String host;
//
//    @Value("${spring.mail.port}")
//    private int port;

    @Bean
    public JavaMailSender javaMailSender()
    {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(578);

        javaMailSender.setUsername("testingdevnew@gmail.com");
        javaMailSender.setPassword("vyjzqtthniukuvlr");

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return javaMailSender;
    }

//    @Bean
//    public SimpleMailMessage emailTemplate()
//    {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo("somebody@gmail.com");
//        message.setFrom("testingdevnew@gmail.com");
//        message.setText("FATAL - Application crash. Save your job !!");
//        return message;
//    }
}
