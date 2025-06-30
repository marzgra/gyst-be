package com.gyst.goal.application;

import com.gyst.goal.domain.model.Goal;
import com.gyst.goal.domain.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetGoalsQuery {

    private final GoalRepository repository;

    public List<Goal> getGoalsByUserId(Long userId) {
        return repository.findByUserId(userId);
    }
}
