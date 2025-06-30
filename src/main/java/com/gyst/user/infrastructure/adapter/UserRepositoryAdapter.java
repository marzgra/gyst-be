package com.gyst.user.infrastructure.adapter;

import com.gyst.user.domain.mapper.UserMapper;
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
    private final UserMapper mapper;

    @Override
    public User save(User user) {
        UserEntity savedUser = jpaRepo.save(mapper.toEntity(user));
        return mapper.toDomain(savedUser);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepo.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepo.findByEmail(email)
                .map(mapper::toDomain);
    }
}

