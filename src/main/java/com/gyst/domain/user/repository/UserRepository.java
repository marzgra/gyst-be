package com.gyst.domain.user.repository;

import com.gyst.domain.user.model.User;

import java.util.Optional;

/**
 * Defines contract for storing and retrieving users.
 * Independent from technology (e.g., database, in-memory, etc.)
 * Domain says what it want, not how it works 0 implementation will come in later.
 */

public interface UserRepository {
    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);
}
