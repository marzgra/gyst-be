package com.gyst.mapper;

import com.gyst.dto.TaskDto;
import com.gyst.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toTask(TaskDto dto);
    TaskDto toDto(Task task);
}
