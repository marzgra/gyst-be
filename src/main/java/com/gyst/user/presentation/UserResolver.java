package com.gyst.user.presentation;

import com.gyst.user.application.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

/**
 * GraphQL resolver – handles external request and delegates to application logic.
 */
@Controller
@RequiredArgsConstructor
public class UserResolver {

    private final RegisterUserUseCase registerUserUseCase;

    @MutationMapping
    public Long createUser(@Argument String email, @Argument String name) {
        return registerUserUseCase.register(email, name);
    }
}
