package com.gyst.service;

import com.gyst.dto.GoalDto;
import com.gyst.entity.Goal;
import com.gyst.mapper.GoalMapper;
import com.gyst.repository.GoalRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GoalService {
    private final GoalRepository goalRepository;
    private final GoalMapper goalMapper;

    public GoalDto createGoal(GoalDto goalDto) {
        Goal saved = goalRepository.save(goalMapper.toEntity(goalDto));
        return goalMapper.toDto(saved);
    }
}
