package com.employee.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.employee.payload.dto.CreateTaskRequestDTO;
import com.employee.payload.dto.UpdateTaskRequestDTO;
import com.employee.payload.response.APIResponse;
import com.employee.payload.response.TaskResponseDTO;
import com.employee.service.TaskService;

import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("api/admin/")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("assignTask")
    public ResponseEntity<APIResponse<TaskResponseDTO>> assignTaskToEmployee(
            @RequestBody CreateTaskRequestDTO createTaskRequestDTO) {
        return ResponseEntity.ok(taskService.assignTaskToEmployee(createTaskRequestDTO));
    }

    @PostMapping("assignTaskForSelf")
    public ResponseEntity<APIResponse<TaskResponseDTO>> assignTaskToSelf(
            @RequestBody CreateTaskRequestDTO createTaskRequestDTO) {
        return ResponseEntity.ok(taskService.createPersonalTask(createTaskRequestDTO));
    }

    @GetMapping("all-tasks")
    public ResponseEntity<APIResponse<List<TaskResponseDTO>>> getAllTasks() {

        return ResponseEntity.ok(taskService.getAllTasks());

    }

    @PutMapping("edit-task/{taskId}")
    public ResponseEntity<APIResponse<TaskResponseDTO>> editTask(
            @PathVariable Long taskId,
            @RequestBody UpdateTaskRequestDTO request) {
        return ResponseEntity.ok(taskService.editTask(taskId, request));
    }

    @DeleteMapping("delete-task/{taskId}")
    public ResponseEntity<APIResponse> deleteTaskByTaskId(
            @PathVariable Long taskId){


        return ResponseEntity.ok(taskService.deleteTask(taskId));
    }
}
