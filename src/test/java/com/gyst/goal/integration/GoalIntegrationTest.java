//package com.gyst.goal.integration;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import static com.gyst.TestConstants.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Testcontainers
//@ActiveProfiles("test")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@AutoConfigureMockMvc
//public class GoalIntegrationTest {
//
//    @Container
//    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15").withDatabaseName("testdb")
//            .withUsername("test")
//            .withPassword("test");
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @DynamicPropertySource
//    static void overrideProps(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", postgres::getJdbcUrl);
//        registry.add("spring.datasource.username", postgres::getUsername);
//        registry.add("spring.datasource.password", postgres::getPassword);
//    }
//
//    @Test
//    void createGoal_ShouldCreateGoal() throws Exception {
//        String createGoalMutation = """
//                mutation {
//                    createGoal(userId: "%s", title: "%s", description: "%s", deadline: "%s", type: "%s") {
//                        id
//                        userId
//                        title
//                        description
//                        deadline
//                        type
//                        status
//                    }
//                }
//                """.formatted(USER_ID, GOAL_NAME, GOAL_DESC, GOAL_DEADLINE, GOAL_TYPE);
//
//        String graphqlPayload = """
//                {
//                  "query": "%s"
//                }
//                """.formatted(createGoalMutation.replace("\"", "\\\"")
//                .replace("\n", " "));
//
//        mockMvc.perform(post("/graphql").contentType(MediaType.APPLICATION_JSON)
//                        .content(graphqlPayload))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.createGoal.userId").value(USER_ID))
//                .andExpect(jsonPath("$.data.createGoal.title").value(GOAL_NAME))
//                .andExpect(jsonPath("$.data.createGoal.description").value(GOAL_DESC))
//                .andExpect(jsonPath("$.data.createGoal.deadline").value(GOAL_DEADLINE))
//                .andExpect(jsonPath("$.data.createGoal.type").value(GOAL_TYPE))
//                .andExpect(jsonPath("$.data.createGoal.status").value("CREATED"));
//    }
//
//    @Test
//    void updateGoal_ShouldUpdateGoal() throws Exception {
//        String createGoalMutation = """
//                mutation {
//                    createGoal(userId: "%s", title: "%s", description: "%s", deadline: "%s", type: "%s") {
//                        id
//                    }
//                }
//                """.formatted(USER_ID, GOAL_NAME, GOAL_DESC, GOAL_DEADLINE, GOAL_TYPE);
//
//        String createPayload = """
//                {
//                  "query": "%s"
//                }
//                """.formatted(createGoalMutation.replace("\"", "\\\"")
//                .replace("\n", " "));
//
//        mockMvc.perform(post("/graphql").contentType(MediaType.APPLICATION_JSON)
//                        .content(createPayload))
//                .andExpect(status().isOk());
//
//        String updateGoalMutation = """
//                mutation {
//                    updateGoal(id: "%s", userId: "%s", title: "%s", description: "%s", deadline: "%s", type: "%s") {
//                        id
//                        userId
//                        title
//                        description
//                        deadline
//                        type
//                        status
//                    }
//                }
//                """.formatted(GOAL_ID, USER_ID, GOAL_NEW_NAME, GOAL_NEW_DESC, GOAL_NEW_DEADLINE, GOAL_NEW_TYPE);
//
//        String graphqlPayload = """
//                {
//                  "query": "%s"
//                }
//                """.formatted(updateGoalMutation.replace("\"", "\\\"")
//                .replace("\n", " "));
//
//        mockMvc.perform(post("/graphql").contentType(MediaType.APPLICATION_JSON)
//                        .content(graphqlPayload))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.updateGoal.userId").value(USER_ID))
//                .andExpect(jsonPath("$.data.updateGoal.title").value(GOAL_NEW_NAME))
//                .andExpect(jsonPath("$.data.updateGoal.description").value(GOAL_NEW_DESC))
//                .andExpect(jsonPath("$.data.updateGoal.deadline").value(GOAL_NEW_DEADLINE))
//                .andExpect(jsonPath("$.data.updateGoal.type").value(GOAL_NEW_TYPE));
//    }
//
//    @Test
//    void getGoal_ShouldReturnGoal() throws Exception {
//        String createGoalMutation = """
//                mutation {
//                    createGoal(userId: "%s", title: "%s", description: "%s", deadline: "%s", type: "%s") {
//                        id
//                    }
//                }
//                """.formatted(USER_ID, GOAL_NAME, GOAL_DESC, GOAL_DEADLINE, GOAL_TYPE);
//
//        String createPayload = """
//                {
//                  "query": "%s"
//                }
//                """.formatted(createGoalMutation.replace("\"", "\\\"")
//                .replace("\n", " "));
//
//        mockMvc.perform(post("/graphql").contentType(MediaType.APPLICATION_JSON)
//                        .content(createPayload))
//                .andExpect(status().isOk());
//
//        String getGoalQuery = """
//                query {
//                    getGoal(id: "%s", userId: "%s") {
//                        id
//                        userId
//                        title
//                        description
//                        deadline
//                        type
//                        status
//                    }
//                }
//                """.formatted(GOAL_ID, USER_ID);
//
//        String getPayload = """
//                {
//                  "query": "%s"
//                }
//                """.formatted(getGoalQuery.replace("\"", "\\\"")
//                .replace("\n", " "));
//
//        mockMvc.perform(post("/graphql").contentType(MediaType.APPLICATION_JSON)
//                        .content(getPayload))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.getGoal.userId").value(USER_ID))
//                .andExpect(jsonPath("$.data.getGoal.title").value(GOAL_NAME))
//                .andExpect(jsonPath("$.data.getGoal.description").value(GOAL_DESC))
//                .andExpect(jsonPath("$.data.getGoal.deadline").value(GOAL_DEADLINE))
//                .andExpect(jsonPath("$.data.getGoal.type").value(GOAL_TYPE))
//                .andExpect(jsonPath("$.data.getGoal.status").value("CREATED"));
//
//    }
//}