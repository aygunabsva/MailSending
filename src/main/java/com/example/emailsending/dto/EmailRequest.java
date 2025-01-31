package com.example.emailsending.dto;

import lombok.Data;

@Data
public class EmailRequest {
    private String to;
    private String from;
    private String subject;
    private String text;
}