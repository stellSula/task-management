package com.example.task_management.services.impl;

import com.example.task_management.services.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendTaskCreatedEmail(String to, String taskTitle) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("New task created: " + taskTitle);
        message.setText("A new task has been created: " + taskTitle);
        emailSender.send(message);
    }

}
