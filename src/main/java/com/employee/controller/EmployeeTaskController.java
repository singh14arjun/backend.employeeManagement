package com.employee.controller;

import com.employee.payload.dto.CreateTaskRequestDTO;
import com.employee.payload.response.APIResponse;
import com.employee.payload.response.TaskResponseDTO;
import com.employee.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employee/tasks/")
@RequiredArgsConstructor
public class EmployeeTaskController {

    private final TaskService taskService;

    @PostMapping("{employeeId}")
    public ResponseEntity<APIResponse<TaskResponseDTO>> createTask(
            @PathVariable Long employeeId, @Valid @RequestParam CreateTaskRequestDTO createTaskRequestDTO
            ){

        return ResponseEntity.ok(taskService.createPersonalTask(createTaskRequestDTO));
    }

    @GetMapping("{employeeId}")
    public ResponseEntity<APIResponse<List<TaskResponseDTO>>> getEmployeeTasks(@PathVariable Long employeeId){
       System.out.println("Insided");
        return ResponseEntity.ok(taskService.getAllTasksByUserId(employeeId));
    }
}
