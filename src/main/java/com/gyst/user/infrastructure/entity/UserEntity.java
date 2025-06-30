package com.gyst.user.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * UserEntity represents a user in the DB.
 */

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String name;
}
