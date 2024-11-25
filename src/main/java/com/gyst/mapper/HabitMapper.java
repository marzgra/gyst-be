package com.gyst.mapper;

import com.gyst.dto.HabitDto;
import com.gyst.entity.Habit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HabitMapper {
    Habit toTask(HabitDto dto);

    HabitDto toDto(Habit task);
}
