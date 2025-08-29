//package com.gyst.user.integration;
//
//import com.github.database.rider.core.api.dataset.DataSet;
//import com.github.database.rider.spring.api.DBRider;
//import com.gyst.config.TestContainersConfig;
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
//@DBRider
//public class UserIntegrationTest extends TestContainersConfig {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    @DataSet("datasets/test-data.yml")
//    void getUser_ShouldReturnExistingUser() throws Exception {
//        String query = """
//                query {
//                    getUser(id: "1") {
//                        id
//                        email
//                        name
//                    }
//                }
//                """;
//
//        String graphqlPayload = """
//                {
//                  "query": "%s"
//                }
//                """.formatted(query.replace("\"", "\\\"")
//                .replace("\n", " "));
//
//        mockMvc.perform(post("/graphql")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(graphqlPayload))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.getUser.email").value("test@test.com"))
//                .andExpect(jsonPath("$.data.getUser.name").value("Test User"));
//    }
//
//    @Test
//    @DataSet("datasets/test-data.yml")
//    void registerUser_ShouldCreateUser() throws Exception {
//        String registerMutation = """
//                mutation {
//                    createGoal(userId: "%s", title: "%s", description: "%s", deadline: "%s", type: "%s")
//                }
//                """.formatted(USER_ID, GOAL_NAME, GOAL_DESC, GOAL_DEADLINE, GOAL_TYPE);
//
//        String graphqlPayload = """
//                {
//                  "query": "%s"
//                }
//                """.formatted(registerMutation.replace("\"", "\\\"")
//                .replace("\n", " "));
//
//        mockMvc.perform(post("/graphql").contentType(MediaType.APPLICATION_JSON)
//                        .content(graphqlPayload))
//                .andExpect(status().isOk());
//    }
//}
