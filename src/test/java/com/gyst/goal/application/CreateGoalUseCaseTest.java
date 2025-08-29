package com.gyst.goal.application;

import com.gyst.goal.domain.Goal;
import com.gyst.goal.domain.GoalStatus;
import com.gyst.goal.domain.GoalType;
import com.gyst.goal.domain.GoalRepository;
import org.junit.Test;
import org.mockito.Mockito;

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
                .goalId(GOAL_ID)
                .userId(USER_ID)
                .title(GOAL_NAME)
                .description(GOAL_DESC)
                .deadline(GOAL_DEADLINE)
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
