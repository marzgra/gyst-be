package com.gyst.user.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.gyst.TestConstants.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15").withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");
    @Autowired
    private MockMvc mockMvc;

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    void registerUser_ShouldCreateUser() throws Exception {
        String registerMutation = """
                mutation {
                    createGoal(userId: "%s", title: "%s", description: "%s", deadline: "%s", type: "%s")
                }
                """.formatted(USER_ID, GOAL_NAME, GOAL_DESC, GOAL_DEADLINE, GOAL_TYPE);

        String graphqlPayload = """
                {
                  "query": "%s"
                }
                """.formatted(registerMutation.replace("\"", "\\\"")
                .replace("\n", " "));

        mockMvc.perform(post("/graphql").contentType(MediaType.APPLICATION_JSON)
                        .content(graphqlPayload))
                .andExpect(status().isOk());
    }
}
