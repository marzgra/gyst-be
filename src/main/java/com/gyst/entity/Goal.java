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
    @Column
    private Long id;

    @Column
    private String goalName;

    @Column
    private LocalDate deadline;

    @Column
    private GoalType type;

    @OneToMany
    private List<Goal> subGoals;

    @ManyToOne
    private User author;
}
