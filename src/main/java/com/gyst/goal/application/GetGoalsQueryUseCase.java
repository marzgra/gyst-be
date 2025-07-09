package com.gyst.goal.application;

import com.gyst.goal.domain.model.Goal;
import com.gyst.goal.domain.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetGoalsQueryUseCase {

    private final GoalRepository repository;

    public List<Goal> getGoalsByUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return repository.findByUserId(userId);
    }
}
