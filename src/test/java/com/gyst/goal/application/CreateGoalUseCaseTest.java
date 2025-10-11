package com.gyst.goal.application;

import com.gyst.goal.domain.Goal;
import com.gyst.goal.domain.GoalRepository;
import org.junit.Test;
import org.mockito.Mockito;

import static com.gyst.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CreateGoalUseCaseTest {

    private final GoalRepository goalRepository = Mockito.mock(GoalRepository.class);

    private final CreateGoalUseCase useCase = new CreateGoalUseCase(goalRepository);

    @Test
    public void shouldCreateGoal() {
        // given
        Goal savedGoal = Goal.builder()
                .goalId(GOAL_ID)
                .userId(USER_ID)
                .title(GOAL_NAME)
                .description(GOAL_DESC)
                .deadline(GOAL_DEADLINE)
                .type(GOAL_TYPE_YEAR)
                .status(GOAL_STATUS_CREATED)
                .build();

        when(goalRepository.save(any(Goal.class))).thenReturn(savedGoal);

        // when
        Goal createdGoal = useCase.createGoal(USER_ID, GOAL_NAME, GOAL_DESC, GOAL_DEADLINE, GOAL_TYPE_YEAR);

        // then
        assertEquals(savedGoal, createdGoal);
    }
}
