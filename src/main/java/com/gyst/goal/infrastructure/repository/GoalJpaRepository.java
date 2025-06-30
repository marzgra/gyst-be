package com.gyst.goal.infrastructure.repository;

import com.gyst.goal.infrastructure.entity.GoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoalJpaRepository extends JpaRepository<GoalEntity, Long> {
    List<GoalEntity> findByUserId(Long userId);
    Optional<GoalEntity> findByIdAndUserId(Long id, Long userId);
}
