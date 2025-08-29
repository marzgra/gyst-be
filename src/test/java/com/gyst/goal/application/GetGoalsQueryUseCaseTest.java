package com.gyst.goal.application;

import com.gyst.goal.domain.Goal;
import com.gyst.goal.domain.GoalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class GetGoalsQueryUseCaseTest {

    @Mock
    private GoalRepository goalRepository;

    @InjectMocks
    private GetGoalsQueryUseCase getGoalsQueryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnGoalsForValidUserId() {
        //given
        Long userId = 1L;
        List<Goal> goals = List.of(new Goal(), new Goal());
        when(goalRepository.findByUserId(userId)).thenReturn(goals);

        //when
        List<Goal> result = getGoalsQueryUseCase.getGoalsByUserId(userId);

        //then
        assertEquals(goals, result);
    }

    @Test
    void shouldReturnEmptyListForNonExistentUserId() {
        //given
        Long userId = 999L;
        when(goalRepository.findByUserId(userId)).thenReturn(Collections.emptyList());

        //when
        List<Goal> result = getGoalsQueryUseCase.getGoalsByUserId(userId);

        //then
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void shouldThrowExceptionForNullUserId() {
        // given
        Long userId = null;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> getGoalsQueryUseCase.getGoalsByUserId(userId));
    }
}
