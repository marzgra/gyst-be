package com.gyst.goal.domain;

import com.gyst.goal.infrastructure.GoalEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GoalMapper {
    Goal toDomain(GoalEntity goalEntity);
    GoalEntity toEntity(Goal goal);
}
