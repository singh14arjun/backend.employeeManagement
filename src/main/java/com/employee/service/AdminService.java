package com.employee.service;

import com.employee.constant.TaskPriority;
import com.employee.constant.TasksStatus;
import com.employee.model.User;
import com.employee.payload.dto.*;
import com.employee.payload.response.APIResponse;
import com.employee.payload.response.TaskResponseDTO;
import com.employee.payload.response.UserResponseDTO;

import java.util.List;

public interface AdminService {
    public APIResponse<UserResponseDTO> createUser(UserRegisterDTO user);
    public APIResponse<UserResponseDTO> getUserById(Long id);
    public  APIResponse<List<UserResponseDTO>> getAllUser();
    public APIResponse deleteUser(Long id);
    public APIResponse<UserResponseDTO> updateUser(Long id, UserUpdateDTO userUpdateDTO);
    public APIResponse<String> login(String email, String password);

    interface TaskService {

        // Create
        TaskResponseDTO createTask(CreateTaskRequestDTO request);

        TaskResponseDTO createPersonalTask(CreateTaskRequestDTO request);

        // Read
        TaskResponseDTO getTaskById(Long taskId);

        List<TaskResponseDTO> getAllTasks();

        List<TaskResponseDTO> getTasksByUserId(Long userId);

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
}
