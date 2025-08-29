package com.gyst.goal.application;

import com.gyst.goal.domain.Goal;
import com.gyst.goal.domain.GoalRepository;
import com.gyst.goal.domain.GoalType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateGoalUseCase {

    private final GoalRepository repository;

    public Goal createGoal(Long userId, String title, String description, String deadline, String type) {
        Goal goal = new Goal(
                userId,
                title,
                description,
                deadline,
                GoalType.valueOf(type)
        );
        return repository.save(goal);
    }
}
