package com.gyst.goal.presentation;

import com.gyst.config.GraphQlConfig;
import com.gyst.goal.application.CreateGoalUseCase;
import com.gyst.goal.application.GetGoalsQueryUseCase;
import com.gyst.goal.application.UpdateGoalUseCase;
import com.gyst.goal.domain.Goal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static com.gyst.TestConstants.*;

@GraphQlTest(GoalResolver.class)
@Import(GraphQlConfig.class)
public class GoalResolverTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockitoBean
    private CreateGoalUseCase createGoalUseCase;

    @MockitoBean
    private UpdateGoalUseCase updateGoalUseCase;

    @MockitoBean
    private GetGoalsQueryUseCase getGoalsQueryUseCase;

    @BeforeEach
    void resetMocks() {
        Mockito.reset(createGoalUseCase, updateGoalUseCase, getGoalsQueryUseCase);
    }

    @Test
    public void shouldCreateGoal() {
        // given
        Mockito.when(createGoalUseCase.createGoal(USER_ID, GOAL_NAME, GOAL_DESC, GOAL_DEADLINE, GOAL_TYPE))
                .thenReturn(BASE_GOAL);

        System.out.println("##### from mock: ");
        System.out.println(BASE_GOAL.toString());

        // when + then
        graphQlTester.document("""
                        mutation {
                            createGoal(
                                userId: "%s",\s
                                title: "%s",\s
                                description: "%s",\s
                                deadline: "%s",\s
                                type: "%s"
                            ) {
                                id
                                userId
                                title
                                description
                                deadline
                                type
                                status
                            }
                        }
                        """.formatted(USER_ID, GOAL_NAME, GOAL_DESC, GOAL_DEADLINE, GOAL_TYPE))
                .execute()
                .path("createGoal")
                .entity(Goal.class).satisfies(goal -> {
                    System.out.println("##### from test: ");
                    System.out.println(goal.toString());
                    Assertions.assertNotNull(goal);
                    Assertions.assertEquals(GOAL_ID, goal.getGoalId());
                    Assertions.assertEquals(GOAL_NAME, goal.getTitle());
                    Assertions.assertEquals(GOAL_DESC, goal.getDescription());
                    Assertions.assertEquals(GOAL_DEADLINE, goal.getDeadline());
                    Assertions.assertEquals(GOAL_TYPE, goal.getType());
                });
    }
//    @Test
//    public void shouldUpdateGoal() {
//        // given
//        Mockito.when(updateGoalUseCase.updateGoal(GOAL_ID, USER_ID, GOAL_NEW_NAME, GOAL_NEW_DESC, GOAL_NEW_DEADLINE, GOAL_NEW_TYPE, GOAL_NEW_STATUS))
//                .thenReturn(UPDATED_GOAL);
//
//        // when + then
//        graphQlTester.document("""
//                        mutation {
//                            updateGoal(
//                                goalId: "%s",\s
//                                userId: "%s",\s
//                                title: "%s",\s
//                                description: "%s",\s
//                                deadline: "%s",\s
//                                type: "%s",\s
//                                status: "%s"
//                            ) {
//                                id
//                                userId
//                                title
//                                description
//                                deadline
//                                type
//                                status
//                            }
//                        }
//                        """.formatted(GOAL_ID, USER_ID, GOAL_NEW_NAME, GOAL_NEW_DESC, GOAL_NEW_DEADLINE, GOAL_NEW_TYPE, GOAL_NEW_STATUS))
//                .execute()
//                .path("updateGoal")
//                .entity(Goal.class).satisfies(goal -> {
//                    Assertions.assertNotNull(goal);
//                    Assertions.assertEquals(GOAL_ID, goal.getId());
//                    Assertions.assertEquals(USER_ID, goal.getUserId());
//                    Assertions.assertEquals(GOAL_NEW_NAME, goal.getTitle());
//                    Assertions.assertEquals(GOAL_NEW_DESC, goal.getDescription());
//                    Assertions.assertEquals(GOAL_NEW_DEADLINE, goal.getDeadline());
//                    Assertions.assertEquals(GOAL_NEW_TYPE, goal.getType());
//                    Assertions.assertEquals(GOAL_NEW_STATUS, goal.getStatus());
//                });
//    }
}
