package com.gyst.goal.application;

import com.gyst.goal.domain.GoalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UpdateGoalUseCaseTest {

    @Mock
    private GoalRepository goalRepository;

    @InjectMocks
    private UpdateGoalUseCase updateGoalUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void shouldUpdateGoalSuccessfully() {
//        // given
//        given(goalRepository.findByIdAndUserId(GOAL_ID, USER_ID)).willReturn(Optional.of(BASE_GOAL));
//        given(goalRepository.save(any(Goal.class))).willReturn(UPDATED_GOAL);
//
//        // when
//        Goal updatedGoal = updateGoalUseCase.updateGoal(GOAL_ID, USER_ID, GOAL_NEW_NAME, GOAL_NEW_DESC,
//                GOAL_NEW_DEADLINE, GOAL_NEW_TYPE, GOAL_NEW_STATUS);
//
//        // then
//        verify(goalRepository).save(any(Goal.class));
//        assertEquals(GOAL_ID, updatedGoal.getId());
//        assertEquals(USER_ID, updatedGoal.getUserId());
//        assertEquals(GOAL_NEW_NAME, updatedGoal.getTitle());
//        assertEquals(GOAL_NEW_DESC, updatedGoal.getDescription());
//        assertEquals(GOAL_NEW_DEADLINE, updatedGoal.getDeadline());
//        assertEquals(GOAL_NEW_TYPE, updatedGoal.getType());
//    }
}
