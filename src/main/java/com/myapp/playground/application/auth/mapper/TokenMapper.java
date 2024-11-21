package com.myapp.playground.application.auth.mapper;

import com.myapp.playground.domain.auth.entity.UserToken;
import com.myapp.playground.presentation.auth.dto.TokenResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TokenMapper {

    TokenMapper INSTANCE = Mappers.getMapper(TokenMapper.class);

}
