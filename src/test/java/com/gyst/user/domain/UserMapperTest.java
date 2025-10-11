package com.gyst.user.domain;

import com.gyst.user.infrastructure.UserEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Test
    void shouldMapEntityToDomain() {
        // given
        UserEntity entity = new UserEntity();
        entity.setUserId(1L);
        entity.setEmail("test@example.com");
        entity.setName("Test User");

        // when
        User domain = mapper.toDomain(entity);

        // then
        assertThat(domain)
                .isNotNull()
                .satisfies(user -> {
                    assertThat(user.getUserId()).isEqualTo(1L);
                    assertThat(user.getEmail()).isEqualTo("test@example.com");
                    assertThat(user.getName()).isEqualTo("Test User");
                });
    }

    @Test
    void shouldMapDomainToEntity() {
        // given
        User domain = User.builder()
                .userId(1L)
                .email("test@example.com")
                .name("Test User")
                .build();

        // when
        UserEntity entity = mapper.toEntity(domain);

        // then
        assertThat(entity)
                .isNotNull()
                .satisfies(user -> {
                    assertThat(user.getUserId()).isEqualTo(1L);
                    assertThat(user.getEmail()).isEqualTo("test@example.com");
                    assertThat(user.getName()).isEqualTo("Test User");
                });
    }

    @Test
    void shouldHandleNullValues() {
        // given
        UserEntity entity = null;
        User domain = null;

        // when
        User mappedDomain = mapper.toDomain(entity);
        UserEntity mappedEntity = mapper.toEntity(domain);

        // then
        assertThat(mappedDomain).isNull();
        assertThat(mappedEntity).isNull();
    }
}