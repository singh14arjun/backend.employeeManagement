package com.employee.service.impl;

import com.employee.constant.AssigendBy;
import com.employee.constant.TaskPriority;
import com.employee.constant.TasksStatus;
import com.employee.model.Task;
import com.employee.model.User;
import com.employee.payload.dto.CreateTaskRequestDTO;
import com.employee.payload.dto.UpdateTaskRequestDTO;
import com.employee.payload.response.APIResponse;
import com.employee.payload.response.TaskResponseDTO;
import com.employee.payload.response.UserResponseDTO;
import com.employee.repository.AdminRepository;
import com.employee.repository.TaskRepository;
import com.employee.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Priority;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final AdminRepository adminRepository;

    @Override
    public APIResponse<TaskResponseDTO> assignTaskToEmployee(CreateTaskRequestDTO createTaskRequestDTO) {

        User assignedBy=adminRepository.findById(createTaskRequestDTO.getAssignedBy())
                .orElseThrow(()->new RuntimeException("assigned by user not found"));

        User assignedTo=adminRepository.findById(createTaskRequestDTO.getAssignedTo())
                .orElseThrow(()->new RuntimeException("assigned by user not found"));


        Task task=new Task();
        task.setTitle(createTaskRequestDTO.getTitle());
        task.setDescription(createTaskRequestDTO.getDescription());
        task.setPriority(TaskPriority.LOW);
        task.setDueDateAndTime(LocalDateTime.now());
        task.setStatus(TasksStatus.PENDING);
        task.setAssignedBy(assignedBy);
        task.setAssignedTo(assignedTo);
        task.setCompletedAt(createTaskRequestDTO.getCompletedAt());
        task.setTaskAttachmentList(createTaskRequestDTO.getTaskAttachmentList());
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        Task savedTask = taskRepository.save(task);

        TaskResponseDTO taskResponseDTO = mapToTaskResponseDTO(savedTask);

        return new APIResponse<>(
                true,
                "Task assigened to employee successfully. Task Id : "+savedTask.getId()+" and name of employee is "+assignedTo.getFirstName(),
                         taskResponseDTO
                );
    }

    @Override
    public APIResponse<TaskResponseDTO> createPersonalTask(CreateTaskRequestDTO request) {
        return null ;
    }
  
    @Override
    public APIResponse<TaskResponseDTO> getTaskById(Long taskId) {
        return null;
    }

    @Override
    public APIResponse<List<TaskResponseDTO>> getAllTasks() {
        return null;
    }

    @Override
    public APIResponse<List<TaskResponseDTO>> getTasksByUserId(Long userId) {
        return null;
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

        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();

        taskResponseDTO.setId(task.getId());
        taskResponseDTO.setTitle(task.getTitle());
        taskResponseDTO.setDescription(task.getDescription());
        taskResponseDTO.setPriority(task.getPriority());
        taskResponseDTO.setDueDateAndTime(task.getDueDateAndTime());
        taskResponseDTO.setStatus(task.getStatus());
        taskResponseDTO.setAssignedBy(task.getAssignedBy() != null ? task.getAssignedBy().getId():null);
        taskResponseDTO.setAssignedTo(task.getAssignedTo() != null ?task.getAssignedTo().getId():null);
        taskResponseDTO.setCompletedAt(task.getCompletedAt());
        taskResponseDTO.setTaskAttachmentList(task.getTaskAttachmentList());

        return taskResponseDTO;
    }
}
