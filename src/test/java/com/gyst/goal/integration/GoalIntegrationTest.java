package com.gyst.goal.integration;

import com.gyst.config.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static com.gyst.TestConstants.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GoalIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    void createGoal_ShouldCreateGoal() throws Exception {
       Long userId =  createUser().getUserId();
        String createGoalMutation = """
                mutation {
                    createGoal(userId: "%s", title: "%s", description: "%s", deadline: "%s", type: %s) {
                        goalId
                        userId
                        title
                        description
                        deadline
                        type
                        status
                    }
                }
                """.formatted(userId, GOAL_NAME, GOAL_DESC, GOAL_DEADLINE, GOAL_TYPE_YEAR);

        String graphqlPayload = """
                {
                  "query": "%s"
                }
                """.formatted(createGoalMutation.replace("\"", "\\\"")
                .replace("\n", " "));

        mockMvc.perform(post("/graphql").contentType(MediaType.APPLICATION_JSON)
                        .content(graphqlPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.createGoal.userId").value(userId))
                .andExpect(jsonPath("$.data.createGoal.title").value(GOAL_NAME))
                .andExpect(jsonPath("$.data.createGoal.description").value(GOAL_DESC))
                .andExpect(jsonPath("$.data.createGoal.deadline").value(GOAL_DEADLINE))
                .andExpect(jsonPath("$.data.createGoal.type").value(GOAL_TYPE_YEAR.name()))
                .andExpect(jsonPath("$.data.createGoal.status").value(GOAL_STATUS_CREATED.name()));
    }

        @Test
        void updateGoal_ShouldUpdateGoal() throws Exception {
            Long userId =  createUser().getUserId();
            Long goalId = createGoal(userId).getGoalId();

            String updateGoalMutation = """
                    mutation {
                        updateGoal(
                            goalId: "%s",
                            userId: "%s",
                            change: {
                                title: "%s",
                                description: "%s",
                                deadline: "%s",
                                type: %s
                            } ) {
                            goalId
                            userId
                            title
                            description
                            deadline
                            type
                            status
                        }
                    }
                    """.formatted(goalId, userId, GOAL_NEW_NAME, GOAL_NEW_DESC, GOAL_NEW_DEADLINE, GOAL_TYPE_QUARTER);

            String graphqlPayload = """
                    {
                      "query": "%s"
                    }
                    """.formatted(updateGoalMutation.replace("\"", "\\\"")
                    .replace("\n", " "));

            mockMvc.perform(post("/graphql").contentType(MediaType.APPLICATION_JSON)
                            .content(graphqlPayload))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data.updateGoal.userId").value(userId))
                    .andExpect(jsonPath("$.data.updateGoal.goalId").value(goalId))
                    .andExpect(jsonPath("$.data.updateGoal.title").value(GOAL_NEW_NAME))
                    .andExpect(jsonPath("$.data.updateGoal.description").value(GOAL_NEW_DESC))
                    .andExpect(jsonPath("$.data.updateGoal.deadline").value(GOAL_NEW_DEADLINE))
                    .andExpect(jsonPath("$.data.updateGoal.type").value(GOAL_TYPE_QUARTER.name()))
                    .andExpect(jsonPath("$.data.updateGoal.status").value(GOAL_STATUS_CREATED.name()));
        }

//        @Test
//        void getGoal_ShouldReturnGoal() throws Exception {
//            Long userId =  createUser().getUserId();
//            Long goalId = createGoal(userId).getGoalId();
//
//            String getUsersGoals = """
//                    query {
//                        getUsersGoals(userId: "%s") {
//                            id
//                            userId
//                            title
//                            description
//                            deadline
//                            type
//                            status
//                        }
//                    }
//                    """.formatted(goalId, userId);
//
//            String getPayload = """
//                    {
//                      "query": "%s"
//                    }
//                    """.formatted(getUsersGoals.replace("\"", "\\\"")
//                    .replace("\n", " "));
//
//            mockMvc.perform(post("/graphql").contentType(MediaType.APPLICATION_JSON)
//                            .content(getPayload))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.data.getGoal.userId").value(userId))
//                    .andExpect(jsonPath("$.data.getGoal.goalId").value(goalId))
//                    .andExpect(jsonPath("$.data.getGoal.title").value(GOAL_NAME))
//                    .andExpect(jsonPath("$.data.getGoal.description").value(GOAL_DESC))
//                    .andExpect(jsonPath("$.data.getGoal.deadline").value(GOAL_DEADLINE))
//                    .andExpect(jsonPath("$.data.getGoal.type").value(GOAL_TYPE_YEAR.name()))
//                    .andExpect(jsonPath("$.data.getGoal.status").value(GOAL_STATUS_CREATED.name()));
//
//        }
}