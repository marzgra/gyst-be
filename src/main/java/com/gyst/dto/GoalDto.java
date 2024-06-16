package com.gyst.dto;

import com.gyst.entity.GoalType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GoalDto {
    private Long id;
    private String goalName;
    private LocalDateTime deadline;
    private GoalType type;
    private List<GoalDto> subGoals;
}
