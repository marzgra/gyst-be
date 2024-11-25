package com.gyst.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private String name;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Boolean isDone;

    @ManyToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;

}
