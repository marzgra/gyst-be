package com.gyst.goal.application;

import com.gyst.goal.domain.model.Goal;
import com.gyst.goal.domain.repository.GoalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.gyst.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UpdateGoalUseCaseTest {

    @Mock
    private GoalRepository goalRepository;

    @InjectMocks
    private UpdateGoalUseCase updateGoalUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdateGoalSuccessfully() {
        // given
        given(goalRepository.findByIdAndUserId(GOAL_ID, USER_ID)).willReturn(Optional.of(BASE_GOAL));
        given(goalRepository.save(any())).willReturn(UPDATED_GOAL);

        // when
        Goal updatedGoal = updateGoalUseCase.updateGoal(GOAL_ID, USER_ID, GOAL_NEW_NAME, GOAL_NEW_DESC,
                GOAL_NEW_DEADLINE, GOAL_NEW_TYPE, GOAL_NEW_STATUS);

        // then
        verify(goalRepository).save(any(Goal.class));
        assertEquals(GOAL_ID, updatedGoal.getId());
        assertEquals(USER_ID, updatedGoal.getUserId());
        assertEquals(GOAL_NEW_NAME, updatedGoal.getTitle());
        assertEquals(GOAL_NEW_DESC, updatedGoal.getDescription());
        assertEquals(GOAL_NEW_DEADLINE, updatedGoal.getDeadline());
        assertEquals(GOAL_NEW_TYPE, updatedGoal.getType());
    }
}
