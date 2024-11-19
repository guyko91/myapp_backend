package com.myapp.playground.presentation.common.mapper;

import com.myapp.playground.domain.auth.entity.UserToken;
import com.myapp.playground.presentation.auth.dto.response.TokenResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TokenMapper {

    TokenMapper INSTANCE = Mappers.getMapper(TokenMapper.class);

    @Mapping(target = "accessToken", source = "accessToken")
    @Mapping(target = "refreshToken", source = "refreshToken")
    TokenResponse toResponse(UserToken userToken);

}
