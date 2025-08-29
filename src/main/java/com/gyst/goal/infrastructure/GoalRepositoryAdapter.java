package com.gyst.goal.infrastructure;

import com.gyst.goal.domain.GoalMapper;
import com.gyst.goal.domain.Goal;
import com.gyst.goal.domain.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GoalRepositoryAdapter implements GoalRepository {

    private final GoalJpaRepository jpa;
    private final GoalMapper mapper;

    @Override
    public Goal save(Goal goal) {
        GoalEntity savedGoal = jpa.save(mapper.toEntity(goal));
        return mapper.toDomain(savedGoal);
    }

    @Override
    public Optional<Goal> findByIdAndUserId(Long id, Long userId) {
        return jpa.findByGoalIdAndUserUserId(id, userId).map(mapper::toDomain);
    }

    @Override
    public List<Goal> findByUserId(Long userId) {
        return jpa.findByUserUserId(userId).stream()
                .map(mapper::toDomain)
                .toList();
    }

}

