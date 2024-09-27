package com.example.task_management.services;

import com.example.task_management.dtos.RequestTaskDto;
import com.example.task_management.dtos.TaskDto;
import com.example.task_management.enums.Status;

import java.time.Instant;
import java.util.List;

public interface TaskService {

    Long createTask(TaskDto taskDto);

    List<RequestTaskDto> getAllTasks();

    RequestTaskDto getTaskById(Long id);

    void deleteTask(Long id);

    Long updateTask(Long id, String title, String description, Status status, Instant deadline, Long executorId);

}
