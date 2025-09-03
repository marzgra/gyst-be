package com.gyst.user.presentation;

import com.gyst.config.GraphQlConfig;
import com.gyst.user.application.RegisterUserUseCase;
import com.gyst.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static com.gyst.TestConstants.*;

@GraphQlTest(UserResolver.class)
@Import(GraphQlConfig.class)
class UserResolverTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockitoBean
    private RegisterUserUseCase registerUserUseCase;

    @Test
    void shouldReturnUserId() {
        // given
        Mockito.when(registerUserUseCase.register(USER_EMAIL, USER_NAME))
                .thenReturn(USER);

        // when + then
        graphQlTester.document("""
                        mutation {
                            createUser(name: "%s", email: "%s") {
                                userId
                                name
                                email
                            }
                        }
                        """.formatted(USER_NAME, USER_EMAIL))
                .execute()
                .path("createUser")
                .entity(User.class)
                .satisfies(user -> {
                    Assertions.assertEquals(USER_ID, user.getUserId());
                    Assertions.assertEquals(USER_NAME, user.getName());
                    Assertions.assertEquals(USER_EMAIL, user.getEmail());
                });
    }
}

