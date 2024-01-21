package com.example.demo.services;

import com.example.demo.models.MailInfo;
import jakarta.mail.MessagingException;

public interface MailerService {
    void send(MailInfo mail) throws MessagingException;
    void send(String to, String subject, String body) throws MessagingException;
    void queue(MailInfo mail);
    /**
     * @param to email người nhận
     * @param subject tiêu đề email
     * @param body nội dung email
     */
    void queue(String to, String subject, String body);
}

