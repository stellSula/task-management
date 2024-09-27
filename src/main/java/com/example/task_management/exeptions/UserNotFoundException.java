package com.example.task_management.exeptions;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException() {
        super("User not found!");
    }

}
