package com.gyst.goal.domain;

import com.gyst.goal.application.commands.UpdateGoalCommand;
import com.gyst.goal.infrastructure.GoalEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface GoalMapper {

    @Mapping(source = "user.userId", target="userId")
    Goal toDomain(GoalEntity goalEntity);

    @Mapping(source = "deadline", target = "deadline", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "userId", target="user.userId")
    GoalEntity toEntity(Goal goal);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Goal updateGoalFromCommand(UpdateGoalCommand command, @MappingTarget Goal goal);

}
