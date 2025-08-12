package com.gyst.goal.presentation;

import com.gyst.goal.application.CreateGoalUseCase;
import com.gyst.goal.application.GetGoalsQueryUseCase;
import com.gyst.goal.application.UpdateGoalUseCase;
import com.gyst.goal.domain.Goal;
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
                           @Argument String deadline, @Argument String type) {
        return createGoalUseCase.createGoal(userId, title, description, deadline, type);
    }

    @MutationMapping
    public Goal updateGoal(@Argument Long goalId, @Argument Long userId, @Argument String title, @Argument String description,
                           @Argument String deadline, @Argument String type, @Argument String status) {
        return updateGoalUseCase.updateGoal(goalId, userId, title, description, deadline, type, status);
    }

    @QueryMapping
    public List<Goal> getUserGoals(@Argument Long userId) { // todo: tylko aktywne/ze statusem/filtrowanie po typie
        return getGoalsQueryUseCase.getGoalsByUserId(userId);
    }

}
