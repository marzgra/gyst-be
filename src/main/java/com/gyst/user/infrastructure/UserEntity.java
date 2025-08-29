package com.gyst.user.infrastructure;

import com.gyst.goal.infrastructure.GoalEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

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
    private Long userId;

    private String email;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<GoalEntity> goals;
}
