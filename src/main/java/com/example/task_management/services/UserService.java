package com.example.task_management.services;

import com.example.task_management.dtos.UserDto;
import com.example.task_management.entities.User;

public interface UserService {

    User findById(Long executorId);

    Long createUser(UserDto userDto);

}
