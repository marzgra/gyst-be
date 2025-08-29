package com.gyst.goal.application;

import com.gyst.exceptions.NotFoundException;
import com.gyst.goal.domain.Goal;
import com.gyst.goal.domain.GoalRepository;
import com.gyst.goal.domain.GoalStatus;
import com.gyst.goal.domain.GoalType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateGoalUseCase {

    private final GoalRepository repository;

    public Goal updateGoal(Long goalId, Long userId, String title, String description, String deadline,
                           String type, String status) {
        Goal goal = repository.findByIdAndUserId(goalId, userId)
                .orElseThrow(() -> new NotFoundException("Goal")); // todo exception per domain object?

        goal.update(title, description, deadline, GoalType.valueOf(type), GoalStatus.valueOf(status));
        return repository.save(goal);
    }
}

