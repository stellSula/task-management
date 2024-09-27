package com.example.task_management.exeptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() {
        super("Task not found!");
    }
}
