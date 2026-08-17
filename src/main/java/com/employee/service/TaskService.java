package com.employee.service;


import com.employee.constant.TaskPriority;
import com.employee.constant.TasksStatus;
import com.employee.payload.dto.CreateTaskRequestDTO;
import com.employee.payload.dto.UpdateTaskRequestDTO;
import com.employee.payload.response.APIResponse;
import com.employee.payload.response.TaskResponseDTO;

import java.util.List;

public interface TaskService {

    // Create
    APIResponse<TaskResponseDTO> assignTaskToEmployee(CreateTaskRequestDTO createTaskRequestDTO);

    APIResponse<TaskResponseDTO> createPersonalTask(CreateTaskRequestDTO createTaskRequestDTO);

    // Read
    APIResponse<TaskResponseDTO> getTaskById(Long taskId);

    APIResponse<List<TaskResponseDTO>> getAllTasks();

    APIResponse<List<TaskResponseDTO>> getTasksByUserId(Long userId);

    List<TaskResponseDTO> getTasksAssignedByAdmin(Long adminId);

    List<TaskResponseDTO> getPersonalTasks(Long userId);

    // Update
    TaskResponseDTO updateTask(Long taskId, UpdateTaskRequestDTO request);

    TaskResponseDTO updateTaskStatus(Long taskId, TasksStatus status);

    // Delete
    void deleteTask(Long taskId);

    // Search & Filter
    List<TaskResponseDTO> searchTasks(String keyword);

    List<TaskResponseDTO> filterTasks(
            TasksStatus status,
            TaskPriority priority,
            Long userId
    );

    // Dashboard
    Long getTotalTasks();

    Long getPendingTasks();

    Long getCompletedTasks();

    Long getInProgressTasks();

    Long getOverdueTasks();
}
