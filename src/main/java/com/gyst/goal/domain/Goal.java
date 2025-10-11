package com.gyst.goal.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Goal {
    private Long goalId;
    private Long userId;
    private String title;
    private String description;
    private String deadline;
    private GoalType type;
    private GoalStatus status;

    public Goal(Long userId, String title, String description, String deadline, GoalType type) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.type = type;
        this.status = GoalStatus.CREATED;
    }
}
