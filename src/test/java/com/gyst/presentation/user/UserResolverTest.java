package com.gyst.presentation.user;

import com.gyst.application.user.RegisterUserUseCase;
import com.gyst.config.GraphQlConfig;
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
                .thenReturn(USER_ID);

        // when + then
        graphQlTester.document("""
                        mutation {
                            createUser(name: "%s", email: "%s")
                        }
                        """.formatted(USER_NAME, USER_EMAIL))
                .execute()
                .path("createUser")
                .entity(Long.class)
                .satisfies(id -> Assertions.assertEquals(USER_ID, id));
    }
}

