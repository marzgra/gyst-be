package com.gyst.goal.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
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
        this.status = GoalStatus.CREATED;
    }

    public void update(String title, String description, LocalDate deadline, GoalType type, GoalStatus status) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.type = type;
        this.status = status;
    }

    public void markAsInProgress() { this.status = GoalStatus.IN_PROGRESS; }
    public void markAsDone() { this.status = GoalStatus.DONE; }

    public String getDeadline() {
        return deadline != null ? deadline.toString() : null;
    }

    public String getType() {
        return type != null ? type.name() : null;
    }

    public String getStatus() {
        return status != null ? status.name() : null;
    }
}
