package com.gyst.goal.presentation;

import com.gyst.goal.application.CreateGoalUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class GoalResolver {
    private final CreateGoalUseCase createGoalUseCase;
    //    private final UpdateGoalUseCase updateGoalUseCase;

    @MutationMapping
    public Long createGoal(@Argument Long userId, @Argument String title, @Argument String description,
                           @Argument String deadline, @Argument String type) {
        return createGoalUseCase.createGoal(userId, title, description, deadline, type);
    }
}
