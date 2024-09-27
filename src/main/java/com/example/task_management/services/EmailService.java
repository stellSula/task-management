package com.example.task_management.services;

public interface EmailService {

    void sendTaskCreatedEmail(String to, String taskTitle);

}
