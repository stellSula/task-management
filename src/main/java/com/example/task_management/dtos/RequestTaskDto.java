package com.example.task_management.dtos;

import com.example.task_management.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record RequestTaskDto(
        Long id,
        String title,
        String description,
        Status status,
        @JsonProperty("created_date") Instant createdDate,
        @JsonProperty("updated_date") Instant updatedDate,
        Instant deadline,
        @JsonProperty("executor") RequestUserDto executor) {
}
