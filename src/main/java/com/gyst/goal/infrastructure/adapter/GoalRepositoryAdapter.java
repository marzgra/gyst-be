package com.gyst.goal.infrastructure.adapter;

import com.gyst.goal.domain.mapper.GoalMapper;
import com.gyst.goal.domain.model.Goal;
import com.gyst.goal.domain.repository.GoalRepository;
import com.gyst.goal.infrastructure.entity.GoalEntity;
import com.gyst.goal.infrastructure.repository.GoalJpaRepository;
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
        return jpa.findByIdAndUserId(id, userId).map(mapper::toDomain);
    }

    @Override
    public List<Goal> findByUserId(Long userId) {
        return jpa.findByUserId(userId).stream()
                .map(mapper::toDomain)
                .toList();
    }

}

