package com.employee.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.payload.dto.CreateTaskRequestDTO;
import com.employee.payload.response.APIResponse;
import com.employee.payload.response.TaskResponseDTO;
import com.employee.service.TaskService;

import lombok.RequiredArgsConstructor;

import java.awt.*;

@RestController
@RequiredArgsConstructor
public class TaskController {

    @GetMapping("/message")
    public String message() {
        return "Task Controller";
    }

    private final TaskService taskService;

    @PostMapping("/assignTask")
    public ResponseEntity<APIResponse<TaskResponseDTO>> assignTaskToEmployee(
            @RequestBody CreateTaskRequestDTO createTaskRequestDTO) {
        return ResponseEntity.ok(taskService.assignTaskToEmployee(createTaskRequestDTO));
    }
}
