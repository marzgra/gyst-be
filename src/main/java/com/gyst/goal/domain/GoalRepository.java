package com.gyst.goal.domain;

import java.util.List;
import java.util.Optional;

public interface GoalRepository {
    Goal save(Goal goal);
    Optional<Goal> findByIdAndUserId(Long goalId, Long userId);
    List<Goal> findByUserId(Long userId);
}
