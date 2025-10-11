package com.gyst.goal.application.commands;

import com.gyst.goal.domain.GoalStatus;
import com.gyst.goal.domain.GoalType;

public record UpdateGoalCommand(
        String title,
        String description,
        String deadline,
        GoalType type,
        GoalStatus status
) {}

