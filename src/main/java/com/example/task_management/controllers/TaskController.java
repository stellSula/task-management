package com.example.task_management.controllers;

import com.example.task_management.dtos.RequestTaskDto;
import com.example.task_management.dtos.TaskDto;
import com.example.task_management.enums.Status;
import com.example.task_management.exeptions.EmptyTaskListException;
import com.example.task_management.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createTask(@RequestBody TaskDto taskDto) {
        logger.info("Creating task with info: {}", taskDto.title());
        Long taskId = taskService.createTask(taskDto);
        logger.info("Task created with id: {}", taskId);
        return ResponseEntity.ok(taskId);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<RequestTaskDto>> getAllTasks() {
        logger.info("Fetching all tasks");
        List<RequestTaskDto> tasks = taskService.getAllTasks();

        if (tasks.isEmpty() || tasks == null) {
            logger.warn("No tasks found");
            throw new EmptyTaskListException();
        }

        logger.info("Retrieved {} tasks", tasks.size());
        return ResponseEntity.ok(tasks);
    }

    @PutMapping
    public ResponseEntity<Long> updateTask(
            @RequestParam Long id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Instant deadline,
            @RequestParam(name = "executor_id", required = false) Long executorId) {
        logger.info("Updating task with id: {}", id);
        Long updatedTaskId = taskService.updateTask(id, title, description, status, deadline, executorId);
        logger.info("Task with id:{} was updated", updatedTaskId);
        return ResponseEntity.ok(updatedTaskId);
    }

    @DeleteMapping
    public void deleteTask(@RequestParam Long id) {
        logger.info("Deleting task with id: {}", id);
        taskService.deleteTask(id);
        logger.info("Task with id: {} was deleted", id);
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<RequestTaskDto> getTaskById(@RequestParam Long id) {
        logger.info("Fetching task with id: {}", id);
        RequestTaskDto requestTaskDto = taskService.getTaskById(id);
        logger.info("Retrieved task: {}", requestTaskDto.title());
        return ResponseEntity.ok(requestTaskDto);
    }
}
