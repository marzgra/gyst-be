package com.gyst.goal.application;

import com.gyst.exceptions.NotFoundException;
import com.gyst.goal.domain.model.Goal;
import com.gyst.goal.domain.model.GoalType;
import com.gyst.goal.domain.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UpdateGoalUseCase {

    private final GoalRepository repository;

    public void updateGoal(Long goalId, Long userId, String title, String description, LocalDate deadline,
                           GoalType type) {
        Goal goal = repository.findByIdAndUserId(goalId, userId)
                .orElseThrow(() -> new NotFoundException("Goal"));

        goal.update(title, description, deadline, type);
        repository.save(goal);
    }
}

