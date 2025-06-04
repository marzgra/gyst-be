package com.gyst.user.infrastructure.adapter;

import com.gyst.user.domain.model.User;
import com.gyst.user.domain.repository.UserRepository;
import com.gyst.user.infrastructure.entity.UserEntity;
import com.gyst.user.infrastructure.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Adapter - translated domain <-> persistence model.
 */

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final JpaUserRepository jpaRepo;

    @Override
    public User save(User user) {
        UserEntity savedUser = jpaRepo.save(toEntity(user));
        return toDomain(savedUser);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepo.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepo.findByEmail(email)
                .map(this::toDomain);
    }

    private UserEntity toEntity(User user) {
        return UserEntity.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    private User toDomain(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .name(entity.getName())
                .build();
    }
}

