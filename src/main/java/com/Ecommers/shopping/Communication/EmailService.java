package com.Ecommers.shopping.Communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

//@Service("/emailService")
public class EmailService {


    @Autowired
    private JavaMailSender emailSender;
    public void send(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("testingdevnew@gmail.com");
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);

    }

}
