package com.example.userservice.mapper;

import com.example.userservice.dto.CreateUserDto;
import com.example.userservice.dto.RegistrationUserDto;
import com.example.userservice.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(RegistrationUserDto registrationUserDto);
}
