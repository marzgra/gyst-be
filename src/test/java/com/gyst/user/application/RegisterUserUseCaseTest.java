package com.gyst.user.application;

import com.gyst.user.domain.model.User;
import com.gyst.user.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.gyst.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class RegisterUserUseCaseTest {

    @Test
    public void shouldRegisterUserAndReturnId() {
        // given
        UserRepository mockRepo = Mockito.mock(UserRepository.class);
        User savedUser = User.builder().id(USER_ID).name(USER_NAME).email(USER_EMAIL).build();
        Mockito.when(mockRepo.save(any(User.class))).thenReturn(savedUser);

        RegisterUserUseCase useCase = new RegisterUserUseCase(mockRepo);

        // when
        Long result = useCase.register(USER_EMAIL, USER_NAME);

        // then
        assertEquals(USER_ID, result);
    }
}

