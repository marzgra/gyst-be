package com.gyst.goal.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goal {
    private Long id;
    private Long userId;
    private String title;
    private String description;
    private LocalDate deadline;
    private GoalType type;
    private GoalStatus status;

    public Goal(Long userId, String title, String description, LocalDate deadline, GoalType type) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.type = type;
        this.status = GoalStatus.PLANNED;
    }

    public void update(String title, String description, LocalDate deadline, GoalType type) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.type = type;
    }

    public void markAsInProgress() { this.status = GoalStatus.IN_PROGRESS; }
    public void markAsDone() { this.status = GoalStatus.DONE; }
}
