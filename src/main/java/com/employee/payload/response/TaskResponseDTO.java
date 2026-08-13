package com.employee.payload.response;


import com.employee.constant.TaskPriority;
import com.employee.constant.TasksStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Assigned By
    private Long assignedById;
    private String assignedByName;

    // Assigned To
    private Long assignedToId;
    private String assignedToName;

    // Attachments
    private List<TaskAttachmentResponseDTO> attachments;
}
