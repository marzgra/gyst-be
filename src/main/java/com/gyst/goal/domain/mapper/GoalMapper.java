package com.gyst.goal.domain.mapper;

import com.gyst.goal.domain.model.Goal;
import com.gyst.goal.infrastructure.entity.GoalEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GoalMapper {
    Goal toDomain(GoalEntity goalEntity);
    GoalEntity toEntity(Goal goal);
}
