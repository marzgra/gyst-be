package com.gyst.user.integration;

import com.gyst.config.TestContainersConfig;
import org.dataloader.impl.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;

//@ActiveProfiles("integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureGraphQlTester
class UserIntegrationTest extends TestContainersConfig {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void shouldCreateUserAndReturnUserData() throws IOException {
        graphQlTester.documentName("createUser")
                .execute()
                .path("data.createUser.userId").entity(Long.class).satisfies(Assertions::nonNull)
                .path("data.createUser.email").entity(String.class).isEqualTo("some@sample.user")
                .path("data.createUser.name").entity(String.class).isEqualTo("Some Sample User");
    }

}