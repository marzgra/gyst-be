package com.gyst;

import com.gyst.config.GraphQlConfig;
import com.gyst.goal.application.CreateGoalUseCase;
import com.gyst.goal.presentation.GoalResolver;
import org.junit.jupiter.api.Assertions;
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

    @Test
    public void shouldCreateGoal() {
        // given
        Mockito.when(createGoalUseCase.createGoal(USER_ID, GOAL_NAME, GOAL_DESC, GOAL_DEADLINE, GOAL_TYPE))
                .thenReturn(GOAL_ID);

        // when + then
        graphQlTester.document("""
                        mutation {
                            createGoal(userId: "%s", title: "%s", description: "%s", deadline: "%s", type: "%s")
                        }
                        """.formatted(USER_ID, GOAL_NAME, GOAL_DESC, GOAL_DEADLINE, GOAL_TYPE))
                .execute()
                .path("createGoal")
                .entity(Long.class)
                .satisfies(id -> Assertions.assertEquals(GOAL_ID, id));
    }
}
