package com.example.emailsending.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;

//    @Scheduled(fixedRate = 600000)
//    public void mailSender() throws MessagingException {
//        sendEmail();
//        sendEmailWithAttachment();
//        log.info("Email sent successfully!");
//    }

    public void sendEmail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("aygunabsva@gmail.com");
        msg.setFrom("aygunabsva@yahoo.com");
        msg.setSubject("Testing from Spring Boot");
        msg.setText("Kitab stokdadir");
        javaMailSender.send(msg);
    }

    void sendEmailWithAttachment() throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("aygunabsva@gmail.com");
        helper.setFrom("aygunabsva@yahoo.com");
        helper.setSubject("Testing from Spring Boot");
        helper.setText("<h1>Check attachment for image!</h1>", true);
        helper.addAttachment("java_photo.png", new ClassPathResource("java.png"));
        javaMailSender.send(msg);
    }

    public void sendEmail2(String to, String from, String subject, String text) throws MessagingException {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setFrom(from);
        msg.setSubject(subject);
        msg.setText(text);
        javaMailSender.send(msg);
    }
}