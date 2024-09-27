package com.example.task_management.dtos;

import com.example.task_management.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record TaskDto(
        String title,
        String description,
        Status status,
        Instant deadline,
        @JsonProperty("executor_id") Long executorId) {
}
