package com.example.task_management.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RequestUserDto(
        Long id,
        @JsonProperty("user_name") String userName,
        @JsonProperty("first_name") String firstName,
        @JsonProperty("last_name") String lastName,
        String email,
        @JsonProperty("phone_number") String phoneNumber) {
}
