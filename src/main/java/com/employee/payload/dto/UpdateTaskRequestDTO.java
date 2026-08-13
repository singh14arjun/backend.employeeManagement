package com.employee.payload.dto;


import com.employee.constant.TaskPriority;
import jakarta.validation.constraints.Future;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateTaskRequestDTO {

    private String title;

    private String description;

    private TaskPriority priority;

    @Future(message = "Due date must be in the future")
    private LocalDateTime dueDateAndTime;
}
