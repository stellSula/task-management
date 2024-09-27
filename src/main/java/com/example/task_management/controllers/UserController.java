package com.example.task_management.controllers;

import com.example.task_management.dtos.UserDto;
import com.example.task_management.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createUser(@RequestBody UserDto userDto) {
        logger.info("Creating user with info: {}", userDto.userName());
        Long userId = userService.createUser(userDto);
        logger.info("Task created with id: {}", userId);
        return ResponseEntity.ok(userId);
    }

}
