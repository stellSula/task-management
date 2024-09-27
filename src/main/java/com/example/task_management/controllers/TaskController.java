package com.example.task_management.controllers;

import com.example.task_management.dtos.RequestTaskDto;
import com.example.task_management.dtos.TaskDto;
import com.example.task_management.enums.Status;
import com.example.task_management.exeptions.EmptyTaskListException;
import com.example.task_management.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createTask(@RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.createTask(taskDto));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<RequestTaskDto>> getAllTasks() {
        List<RequestTaskDto> tasks = taskService.getAllTasks();

        if (tasks.isEmpty() || tasks == null) {
            throw new EmptyTaskListException();
        }

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
        return ResponseEntity.ok(taskService.updateTask(id, title, description, status, deadline, executorId));
    }

    @DeleteMapping
    public void deleteTask(@RequestParam Long id) {
        taskService.deleteTask(id);
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<RequestTaskDto> getTaskById(@RequestParam Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }
}
