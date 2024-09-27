package com.example.task_management.services.impl;

import com.example.task_management.dtos.UserDto;
import com.example.task_management.entities.User;
import com.example.task_management.exeptions.UserNotFoundException;
import com.example.task_management.mappers.UserMapper;
import com.example.task_management.repositories.UserRepository;
import com.example.task_management.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User findById(Long executorId) {
        return userRepository.findById(executorId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Long createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        userRepository.save(user);
        return user.getId();
    }
}
