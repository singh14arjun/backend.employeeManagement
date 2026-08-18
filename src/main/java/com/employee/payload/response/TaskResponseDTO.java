package com.employee.payload.response;

import com.employee.constant.TaskPriority;
import com.employee.constant.TasksStatus;
import com.employee.model.TaskAttachment;
import com.employee.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponseDTO {

    private Long id;
    private String title;
    private String description;

    private TasksStatus status;

    private TaskPriority priority;

    private LocalDateTime dueDateAndTime;

    private LocalDateTime completedAt;

    private AssignedUserResponseDTO assignedTo;
    private AssignedUserResponseDTO assignedBy;

    private List<TaskAttachment> taskAttachmentList = new ArrayList<>();
}
