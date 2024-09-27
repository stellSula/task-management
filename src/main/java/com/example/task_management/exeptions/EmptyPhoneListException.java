package com.example.task_management.exeptions;

public class EmptyPhoneListException extends RuntimeException {
    public EmptyPhoneListException() {
        super("List is empty!");
    }
}
