package com.employee.service.impl;

import com.employee.constant.TaskPriority;
import com.employee.constant.TasksStatus;
import com.employee.model.Task;
import com.employee.model.User;
import com.employee.payload.dto.CreateTaskRequestDTO;
import com.employee.payload.dto.UpdateTaskRequestDTO;
import com.employee.payload.response.TaskResponseDTO;
import com.employee.repository.AdminRepository;
import com.employee.repository.TaskRepository;
import com.employee.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final AdminRepository adminRepository;

    @Override
    public TaskResponseDTO createTask(CreateTaskRequestDTO request) {

        User assignedTo = adminRepository.findById(request.getAssignedToUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setDueDateAndTime(request.getDueDateAndTime());

        task.setStatus(TasksStatus.PENDING);

        task.setAssignedTo(assignedTo);
        task.setAssignedBy(getLoggedInUser());

        Task savedTask = taskRepository.save(task);

        return mapToTaskResponseDTO(savedTask);
    }

    @Override
    public TaskResponseDTO createPersonalTask(CreateTaskRequestDTO request) {
        return null;
    }

    @Override
    public TaskResponseDTO getTaskById(Long taskId) {
        return null;
    }

    @Override
    public List<TaskResponseDTO> getAllTasks() {
        return List.of();
    }

    @Override
    public List<TaskResponseDTO> getTasksByUserId(Long userId) {
        return List.of();
    }

    @Override
    public List<TaskResponseDTO> getTasksAssignedByAdmin(Long adminId) {
        return List.of();
    }

    @Override
    public List<TaskResponseDTO> getPersonalTasks(Long userId) {
        return List.of();
    }

    @Override
    public TaskResponseDTO updateTask(Long taskId, UpdateTaskRequestDTO request) {
        return null;
    }

    @Override
    public TaskResponseDTO updateTaskStatus(Long taskId, TasksStatus status) {
        return null;
    }

    @Override
    public void deleteTask(Long taskId) {

    }

    @Override
    public List<TaskResponseDTO> searchTasks(String keyword) {
        return List.of();
    }

    @Override
    public List<TaskResponseDTO> filterTasks(TasksStatus status, TaskPriority priority, Long userId) {
        return List.of();
    }

    @Override
    public Long getTotalTasks() {
        return 0L;
    }

    @Override
    public Long getPendingTasks() {
        return 0L;
    }

    @Override
    public Long getCompletedTasks() {
        return 0L;
    }

    @Override
    public Long getInProgressTasks() {
        return 0L;
    }

    @Override
    public Long getOverdueTasks() {
        return 0L;
    }

    // TODO: Implement actual logic to retrieve the logged-in user
    private User getLoggedInUser() {
        return null;
    }

    private TaskResponseDTO mapToTaskResponseDTO(Task task) {
        if (task == null) {
            return null;
        }

        return TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .dueDateAndTime(task.getDueDateAndTime())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .assignedById(task.getAssignedBy() != null ? task.getAssignedBy().getId() : null)
                .assignedByName(task.getAssignedBy() != null
                        ? task.getAssignedBy().getFirstName() + " " + task.getAssignedBy().getLastName()
                        : null)
                .assignedToId(task.getAssignedTo() != null ? task.getAssignedTo().getId() : null)
                .assignedToName(task.getAssignedTo() != null
                        ? task.getAssignedTo().getFirstName() + " " + task.getAssignedTo().getLastName()
                        : null)
                .build();
    }
}
