package com.gyst.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    @OneToOne
    private Account user;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean isArchived;

    @OneToMany
    private List<HabitExecution> executions;

    // TODO nawyki mogą być różnej częstotliwości
}
