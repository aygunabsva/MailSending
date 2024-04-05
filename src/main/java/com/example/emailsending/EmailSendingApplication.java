package com.example.emailsending;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EmailSendingApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmailSendingApplication.class, args);
    }
}
