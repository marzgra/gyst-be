package com.gyst.goal.application;

import com.gyst.goal.domain.model.Goal;
import com.gyst.goal.domain.model.GoalStatus;
import com.gyst.goal.domain.model.GoalType;
import com.gyst.goal.domain.repository.GoalRepository;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static com.gyst.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class CreateGoalUseCaseTest {

    private final GoalRepository goalRepo = Mockito.mock(GoalRepository.class);

    private final CreateGoalUseCase useCase = new CreateGoalUseCase(goalRepo);

    @Test
    public void shouldCreateGoal() {
        // given
        Goal savedGoal = Goal.builder()
                .id(GOAL_ID)
                .userId(USER_ID)
                .title(GOAL_NAME)
                .description(GOAL_DESC)
                .deadline(LocalDate.parse(GOAL_DEADLINE))
                .type(GoalType.valueOf(GOAL_TYPE))
                .status(GoalStatus.valueOf(GOAL_STATUS))
                .build();
        Mockito.when(goalRepo.save(any(Goal.class)))
                .thenReturn(savedGoal);

        // when
        Goal createdGoal = useCase.createGoal(USER_ID, GOAL_NAME, GOAL_DESC, GOAL_DEADLINE, GOAL_TYPE);

        // then
        assertEquals(savedGoal, createdGoal);
    }
}
