package com.gyst.mapper;

import com.gyst.dto.HabitExecutionDto;
import com.gyst.entity.HabitExecution;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HabitExecutionMapper {
    HabitExecution toTask(HabitExecutionDto dto);

    HabitExecutionDto toDto(HabitExecution task);
}
