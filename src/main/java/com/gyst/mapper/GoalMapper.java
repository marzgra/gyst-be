package com.gyst.mapper;

import com.gyst.dto.GoalDto;
import com.gyst.entity.Goal;
import org.mapstruct.Mapper;

@Mapper
public interface GoalMapper {

    Goal toEntity(GoalDto goalDto);

    GoalDto toDto(Goal goal);
}
