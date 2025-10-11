package com.gyst.goal.application;

import com.gyst.exceptions.NotFoundException;
import com.gyst.goal.application.commands.UpdateGoalCommand;
import com.gyst.goal.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateGoalUseCase {

    private final GoalRepository repository;
    private final GoalMapper mapper;

    public Goal updateGoal(Long goalId, Long userId, UpdateGoalCommand command) {
        Goal goal = repository.findByIdAndUserId(goalId, userId)
                .orElseThrow(() -> new NotFoundException("Goal")); // todo exception per domain object?

        goal = mapper.updateGoalFromCommand(command, goal);
        return repository.save(goal);
    }
}

