package com.gyst.user.application;

import com.gyst.user.domain.User;
import com.gyst.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * This is where the business logic for registering a user is implemented.
 */
@Service
@RequiredArgsConstructor
public class RegisterUserUseCase {

    private final UserRepository userRepository;

    public User register(String email, String name) {
        var user = User.builder()
                .email(email)
                .name(name)
                .build();
        return userRepository.save(user);
    }
}
