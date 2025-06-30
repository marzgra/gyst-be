package com.gyst.user.domain.mapper;

import com.gyst.user.domain.model.User;
import com.gyst.user.infrastructure.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDomain(UserEntity userEntity);

    UserEntity toEntity(User user);

}
