package com.gyst.goal.infrastructure.entity;

import com.gyst.goal.domain.model.GoalStatus;
import com.gyst.goal.domain.model.GoalType;
import com.gyst.user.infrastructure.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "goals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long goalId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;
    private String title;
    private String description;
    private LocalDate deadline;

    @Enumerated(EnumType.STRING)
    private GoalType type;

    @Enumerated(EnumType.STRING)
    private GoalStatus status;
}
