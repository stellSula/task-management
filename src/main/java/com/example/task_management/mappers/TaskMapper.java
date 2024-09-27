package com.example.task_management.mappers;

import com.example.task_management.dtos.RequestTaskDto;
import com.example.task_management.dtos.TaskDto;
import com.example.task_management.entities.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    private final UserMapper userMapper;

    public TaskMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Task toEntity(TaskDto taskDto) {
        Task task = new Task();
        task.setTitle(taskDto.title());
        task.setDescription(taskDto.description());
        task.setStatus(taskDto.status());
        task.setDeadline(taskDto.deadline());
        return task;
    }

    public RequestTaskDto toDto(Task task) {
        return new RequestTaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedDate(),
                task.getUpdatedDate(),
                task.getDeadline(),
                userMapper.toDto(task.getExecutor())
        );
    }
}
