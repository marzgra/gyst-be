package com.gyst.goal.application;

import com.gyst.goal.application.commands.UpdateGoalCommand;
import com.gyst.goal.domain.Goal;
import com.gyst.goal.domain.GoalMapper;
import com.gyst.goal.domain.GoalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.gyst.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UpdateGoalUseCaseTest {

    private final GoalRepository goalRepository = Mockito.mock(GoalRepository.class);
    private final GoalMapper goalMapper = Mockito.mock(GoalMapper.class);

    private final UpdateGoalUseCase updateGoalUseCase = new UpdateGoalUseCase(goalRepository, goalMapper);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdateGoalSuccessfully() {
        // given
        given(goalRepository.findByIdAndUserId(GOAL_ID, USER_ID)).willReturn(Optional.of(BASE_GOAL_WITH_IDS));
        given(goalRepository.save(any(Goal.class))).willReturn(UPDATED_GOAL_WITH_IDS);
        UpdateGoalCommand command = new UpdateGoalCommand(GOAL_NEW_NAME, GOAL_NEW_DESC,
                GOAL_NEW_DEADLINE, GOAL_TYPE_QUARTER, GOAL_STATUS_PLANNED);
        given(goalMapper.updateGoalFromCommand(eq(command), any(Goal.class))).willReturn(UPDATED_GOAL_WITH_IDS);

        // when
        Goal updatedGoal = updateGoalUseCase.updateGoal(GOAL_ID, USER_ID, command);

        // then
        verify(goalRepository).save(any(Goal.class));
        assertEquals(GOAL_ID, updatedGoal.getGoalId());
        assertEquals(USER_ID, updatedGoal.getUserId());
        assertEquals(GOAL_NEW_NAME, updatedGoal.getTitle());
        assertEquals(GOAL_NEW_DESC, updatedGoal.getDescription());
        assertEquals(GOAL_NEW_DEADLINE, updatedGoal.getDeadline());
        assertEquals(GOAL_TYPE_QUARTER, updatedGoal.getType());
    }
}
