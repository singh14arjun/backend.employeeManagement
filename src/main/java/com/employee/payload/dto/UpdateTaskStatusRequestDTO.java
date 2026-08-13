package com.employee.payload.dto;

import com.employee.constant.TasksStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateTaskStatusRequestDTO {

    @NotNull(message = "Status is required")
    private TasksStatus status;
}
