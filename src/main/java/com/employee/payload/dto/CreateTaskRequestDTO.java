package com.employee.payload.dto;

import com.employee.constant.TaskPriority;
import com.employee.constant.TasksStatus;
import com.employee.model.TaskAttachment;
import com.employee.model.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTaskRequestDTO {

    private String title;
    private String description;

    private TasksStatus status;

    private TaskPriority priority;

    private LocalDateTime dueDateAndTime;

    private LocalDateTime completedAt;

    private Long assignedBy;

    private Long assignedTo;

    private List<TaskAttachment> taskAttachmentList = new ArrayList<>();
}
