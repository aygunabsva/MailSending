package com.example.emailsending.controller;

import com.example.emailsending.dto.EmailRequest;
import com.example.emailsending.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private MailService mailService;

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            mailService.sendEmail2(emailRequest.getTo(), emailRequest.getFrom(), emailRequest.getSubject(), emailRequest.getText());
            return "Email sent successfully!";
        } catch (MessagingException e) {
            return "Email sending failed: " + e.getMessage();
        }
    }
}
