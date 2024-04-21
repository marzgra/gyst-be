package com.gyst.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GoalDto {
    private Long id;
    private String goalName;
    private LocalDateTime deadline;
    private List<GoalDto> subGoals;
}
