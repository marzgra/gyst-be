package com.gyst.goal.application;

import com.gyst.goal.domain.Goal;
import com.gyst.goal.domain.GoalType;
import com.gyst.goal.domain.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class CreateGoalUseCase {

    private final GoalRepository repository;

    public Goal createGoal(Long userId, String title, String description, String deadline, String type) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final LocalDate deadlineFormatted = LocalDate.parse(deadline, formatter);
        Goal goal = new Goal(
                userId,
                title,
                description,
                deadlineFormatted,
                GoalType.valueOf(type)
        );
        return repository.save(goal);
    }
}
