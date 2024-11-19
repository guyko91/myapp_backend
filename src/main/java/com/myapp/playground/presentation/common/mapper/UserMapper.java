package com.myapp.playground.presentation.common.mapper;

import com.myapp.playground.domain.user.entity.User;
import com.myapp.playground.presentation.auth.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "picture", source = "picture")
    UserResponse toResponse(User user);

}
