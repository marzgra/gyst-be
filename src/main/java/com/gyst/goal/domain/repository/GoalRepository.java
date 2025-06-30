package com.gyst.goal.domain.repository;

import com.gyst.goal.domain.model.Goal;

import java.util.List;
import java.util.Optional;

public interface GoalRepository {
    Goal save(Goal goal);
    Optional<Goal> findByIdAndUserId(Long goalId, Long userId);
    List<Goal> findByUserId(Long userId);
}
