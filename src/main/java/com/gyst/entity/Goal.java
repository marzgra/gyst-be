package com.gyst.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Data
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String goalName;

    private LocalDate deadline;

    @Enumerated(EnumType.STRING)
    private GoalType type;

    @OneToMany
    private List<Goal> subGoals;

    @ManyToOne
    private Account author;

    @Enumerated(EnumType.STRING)
    private GoalStatus status;

    private int completedTasks;
    private int plannedTasks;

    private int daysLeft;

    private String color;
}
