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

    public Long register(String email, String name) {
        var user = User.builder()
                .email(email)
                .name(name)
                .build();
        var savedUser = userRepository.save(user);
        return savedUser.getId();
    }
}
