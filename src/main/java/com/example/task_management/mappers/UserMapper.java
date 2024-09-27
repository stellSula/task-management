package com.example.task_management.mappers;

import com.example.task_management.dtos.RequestUserDto;
import com.example.task_management.dtos.UserDto;
import com.example.task_management.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.userName());
        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        user.setEmail(userDto.email());
        user.setPhoneNumber(userDto.phoneNumber());
        return user;
    }

    public RequestUserDto toDto(User user) {
        RequestUserDto requestUserDto = new RequestUserDto(
                user.getId(),
                user.getUserName(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber()
        );
        return requestUserDto;
    }

}
