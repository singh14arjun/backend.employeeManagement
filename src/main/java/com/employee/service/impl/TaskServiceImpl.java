package com.employee.service.impl;

import com.employee.constant.AssigendBy;
import com.employee.constant.TaskPriority;
import com.employee.constant.TasksStatus;
import com.employee.mapper.TaskMapper;
import com.employee.model.Task;
import com.employee.model.User;
import com.employee.payload.dto.CreateTaskRequestDTO;
import com.employee.payload.dto.UpdateTaskRequestDTO;
import com.employee.payload.response.APIResponse;
import com.employee.payload.response.AssignedUserResponseDTO;
import com.employee.payload.response.TaskResponseDTO;
import com.employee.payload.response.UserResponseDTO;
import com.employee.repository.AdminRepository;
import com.employee.repository.TaskRepository;
import com.employee.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Priority;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final AdminRepository adminRepository;

    private final TaskMapper taskMapper ;

    @Override
    public APIResponse<TaskResponseDTO> assignTaskToEmployee(CreateTaskRequestDTO createTaskRequestDTO) {

        User assignedBy = adminRepository.findById(createTaskRequestDTO.getAssignedBy())
                .orElseThrow(() -> new RuntimeException("assigned by user not found"));

        User assignedTo = adminRepository.findById(createTaskRequestDTO.getAssignedTo())
                .orElseThrow(() -> new RuntimeException("assigned by user not found"));

        Task task = new Task();
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
                "Task assigned to employee successfully. Task Id : " + savedTask.getId() + " and name of employee is "
                        + assignedTo.getFirstName(),
                taskResponseDTO);
    }

    @Override
    public APIResponse<TaskResponseDTO> createPersonalTask(CreateTaskRequestDTO createTaskRequestDTO) {
        User assignedBy = adminRepository.findById(createTaskRequestDTO.getAssignedBy())
                .orElseThrow(() -> new RuntimeException("assigned by user not found"));

        User assignedTo = adminRepository.findById(createTaskRequestDTO.getAssignedTo())
                .orElseThrow(() -> new RuntimeException("assigned by user not found"));

        Task task = new Task();
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
                "Task assigned to self successfully. Task Id : " + savedTask.getId() + " and name of employee is "
                        + assignedTo.getFirstName(),
                taskResponseDTO);

    }

    @Override
    public APIResponse<TaskResponseDTO> getTaskById(Long taskId) {
        return null;
    }

    @Override
    public APIResponse<List<TaskResponseDTO>> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();

        if (tasks.isEmpty()) {
            return new APIResponse<>(
                    true,
                    "No Task Found",
                    List.of());
        }

        List<TaskResponseDTO> taskResponseDTOS = tasks
                .stream().map(TaskServiceImpl::mapToTaskResponseDTO).toList();

        return new APIResponse<>(
                true,
                "Task List",
                taskResponseDTOS);
    }

    @Override
    public APIResponse<List<TaskResponseDTO>> getAllTasksByUserId(Long userId) {
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
    public APIResponse<TaskResponseDTO> editTask(Long taskId, UpdateTaskRequestDTO request) {

        Task exitingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));

        taskMapper.updateTaskFromDto(request, exitingTask);

        Task savedTask = taskRepository.save(exitingTask);

        TaskResponseDTO taskResponseDTO = mapToTaskResponseDTO(savedTask);

        return new APIResponse<>(
                true,
                "Task updated successfully. Task Id : " + savedTask.getId() + " and name of employee is "
                        + savedTask.getAssignedTo().getFirstName(),
                taskResponseDTO);
    }

    @Override
    public TaskResponseDTO updateTaskStatus(Long taskId, TasksStatus status) {
        return null;
    }

    @Override
    public APIResponse<String> deleteTask(Long taskId) {

        Optional<Task> task=taskRepository.findById(taskId);

        if(task.isEmpty()) {
            return new APIResponse<>(
                    false, "Task not founded with id : " + taskId,
                    null
            );
        }
            taskRepository.delete(task.get());



        return new APIResponse<>(
                true,
                "Task Deleted successfully.",
                "Deleted task with id : " + taskId);
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

    private static TaskResponseDTO mapToTaskResponseDTO(Task task) {

        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();

        taskResponseDTO.setId(task.getId());
        taskResponseDTO.setTitle(task.getTitle());
        taskResponseDTO.setDescription(task.getDescription());
        taskResponseDTO.setPriority(task.getPriority());
        taskResponseDTO.setDueDateAndTime(task.getDueDateAndTime());
        taskResponseDTO.setStatus(task.getStatus());
        if (task.getAssignedTo() != null) {

            User employee = task.getAssignedTo();

            AssignedUserResponseDTO assignedTo = new AssignedUserResponseDTO();

            assignedTo.setId(employee.getId());
            assignedTo.setFirstName(employee.getFirstName());
            assignedTo.setLastName(employee.getLastName());
            assignedTo.setProfileImage(employee.getProfileImage());

            taskResponseDTO.setAssignedTo(assignedTo);
        }
        if (task.getAssignedBy() != null) {

            User admin = task.getAssignedBy();

            AssignedUserResponseDTO assignedBy = new AssignedUserResponseDTO();

            assignedBy.setId(admin.getId());
            assignedBy.setFirstName(admin.getFirstName());
            assignedBy.setLastName(admin.getLastName());
            assignedBy.setProfileImage(admin.getProfileImage());

            taskResponseDTO.setAssignedBy(assignedBy);
        }
        taskResponseDTO.setCompletedAt(task.getCompletedAt());
        taskResponseDTO.setTaskAttachmentList(task.getTaskAttachmentList());

        return taskResponseDTO;
    }
}
