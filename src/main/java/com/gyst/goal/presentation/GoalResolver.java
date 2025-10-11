package com.gyst.goal.presentation;

import com.gyst.goal.application.CreateGoalUseCase;
import com.gyst.goal.application.GetGoalsQueryUseCase;
import com.gyst.goal.application.UpdateGoalUseCase;
import com.gyst.goal.application.commands.UpdateGoalCommand;
import com.gyst.goal.domain.Goal;
import com.gyst.goal.domain.GoalStatus;
import com.gyst.goal.domain.GoalType;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GoalResolver {

    private final CreateGoalUseCase createGoalUseCase;
    private final UpdateGoalUseCase updateGoalUseCase;
    private final GetGoalsQueryUseCase getGoalsQueryUseCase;

    @MutationMapping
    public Goal createGoal(@Argument Long userId, @Argument String title, @Argument String description,
                           @Argument String deadline, @Argument GoalType type) {
        return createGoalUseCase.createGoal(userId, title, description, deadline, type);
    }

    @MutationMapping
    public Goal updateGoal(@Argument Long goalId, @Argument Long userId, @Argument UpdateGoalCommand change) {
        return updateGoalUseCase.updateGoal(goalId, userId, change);
    }

    @QueryMapping
    public List<Goal> getUsersGoals(@Argument Long userId) { // todo: tylko aktywne/ze statusem/filtrowanie po typie
        return getGoalsQueryUseCase.getGoalsByUserId(userId);
    }

}
