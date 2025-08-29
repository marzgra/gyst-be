package com.gyst.goal.domain;

import com.gyst.goal.infrastructure.GoalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GoalMapper {

    @Mapping(source = "user.userId", target="userId")
    Goal toDomain(GoalEntity goalEntity);

    @Mapping(source = "deadline", target = "deadline", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "userId", target="user.userId")
    GoalEntity toEntity(Goal goal);
}
