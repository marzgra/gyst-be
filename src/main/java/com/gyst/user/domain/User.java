package com.gyst.user.domain;

import lombok.Builder;
import lombok.Value;


/**
 * Represents a user in the domain layer - pure business model, independent from DB or frameworks.
 * Contains data structure and rules (validations, etc.)
 * <p>
 * This class is immutable and uses the Builder pattern for instantiation.
 * </p>
 */

@Value
@Builder
public class User {
    Long userId;
    String email;
    String name;
}
