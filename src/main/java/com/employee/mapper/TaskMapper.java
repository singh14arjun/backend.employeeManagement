package com.employee.mapper;

import com.employee.model.Task;
import com.employee.payload.dto.UpdateTaskRequestDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "assignedTo", ignore = true)
    @Mapping(target = "assignedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
     void updateTaskFromDto(
            UpdateTaskRequestDTO request,
            @MappingTarget Task task) ;


}
