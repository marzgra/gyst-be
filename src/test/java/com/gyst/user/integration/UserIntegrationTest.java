package com.gyst.user.integration;

import com.gyst.config.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.gyst.TestConstants.USER_EMAIL;
import static com.gyst.TestConstants.USER_NAME;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class UserIntegrationTest extends BaseIntegrationTest {

    @Test
    void shouldCreateUserAndReturnUserData() throws Exception {
        String createUserQuery = """
                mutation {
                    createUser(
                    email: "%s", name: "%s") {
                        userId
                        email
                        name
                    }
                }
                """.formatted(USER_EMAIL, USER_NAME);

        String graphqlPayload = """
                    {
                      "query": "%s"
                    }
                    """.formatted(createUserQuery.replace("\"", "\\\"")
                .replace("\n", " "));

        mockMvc.perform(post("/graphql").contentType(MediaType.APPLICATION_JSON)
                        .content(graphqlPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("data.createUser.userId").isNumber())
                .andExpect(jsonPath("data.createUser.email").value(USER_EMAIL))
                .andExpect(jsonPath("data.createUser.name").value(USER_NAME));
    }

}