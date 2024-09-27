package com.example.task_management.services.impl;

import com.example.task_management.dtos.RequestTaskDto;
import com.example.task_management.dtos.TaskDto;
import com.example.task_management.entities.Task;
import com.example.task_management.entities.User;
import com.example.task_management.enums.Status;
import com.example.task_management.exeptions.TaskNotFoundException;
import com.example.task_management.mappers.TaskMapper;
import com.example.task_management.repositories.TaskRepository;
import com.example.task_management.services.EmailService;
import com.example.task_management.services.TaskService;
import com.example.task_management.services.UserService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserService userService;
    private final EmailService emailService;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, UserService userService, EmailService emailService) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.userService = userService;
        this.emailService = emailService;
    }

    @Override
    public Long createTask(TaskDto taskDto) {
        User executor = userService.findById(taskDto.executorId());

        Task task = taskMapper.toEntity(taskDto);
        task.setCreatedDate();
        task.setUpdatedDate();
        task.setExecutor(executor);
        taskRepository.save(task);

        //emailService.sendTaskCreatedEmail(executor.getEmail(), task.getTitle());
        return task.getId();
    }

    @Override
    public List<RequestTaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        List<RequestTaskDto> requestTaskDtos = new ArrayList<>();
        for (Task task : tasks) {
            requestTaskDtos.add(taskMapper.toDto(task));
        }
        return requestTaskDtos;
    }

    @Override
    public RequestTaskDto getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        return taskMapper.toDto(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.delete(taskRepository.findById(id).orElseThrow(TaskNotFoundException::new));
    }

    @Override
    public Long updateTask(Long id, String title, String description, Status status, Instant deadline, Long executorId) {
        Task task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);

        if (title != null && !title.isBlank())
            task.setTitle(title);
        if (description != null && !description.isBlank())
            task.setDescription(description);
        if (status != null)
            task.setStatus(status);
        if (deadline != null)
            task.setDeadline(deadline);
        if (executorId != null) {
            User executor = userService.findById(executorId);
            task.setExecutor(executor);
        }
        task.setUpdatedDate();
        taskRepository.save(task);

        return task.getId();
     }

}
