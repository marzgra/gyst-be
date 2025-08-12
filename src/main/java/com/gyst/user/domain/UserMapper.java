package com.gyst.user.domain;

import com.gyst.user.infrastructure.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDomain(UserEntity userEntity);

    UserEntity toEntity(User user);

}
