package com.gyst.goal.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoalJpaRepository extends JpaRepository<GoalEntity, Long> {
    List<GoalEntity> findByUserUserId(Long userId);
    Optional<GoalEntity> findByGoalIdAndUserUserId(Long id, Long userId);
}
