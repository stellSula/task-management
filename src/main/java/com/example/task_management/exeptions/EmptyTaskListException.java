package com.example.task_management.exeptions;

public class EmptyTaskListException extends RuntimeException {
    public EmptyTaskListException() {
        super("List is empty!");
    }
}
