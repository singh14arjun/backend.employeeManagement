package com.employee.service.impl;

import com.employee.mapper.TaskMapper;
import com.employee.model.Task;
import com.employee.payload.dto.UpdateTaskRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {


    public void updateTaskFromDto(
            UpdateTaskRequestDTO request,
            Task task) {

        if (request == null) {
            return;
        }

        if (request.getTitle() != null) {
            task.setTitle(request.getTitle());
        }

        if (request.getDescription() != null) {
            task.setDescription(request.getDescription());
        }

        if (request.getPriority() != null) {
            task.setPriority(request.getPriority());
        }

        if (request.getStatus() != null) {
            task.setStatus(request.getStatus());
        }
    }
}
