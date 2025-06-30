package com.example.accountservice.mapper;

import com.example.accountservice.dto.CreateAccountDto;
import com.example.accountservice.dto.ViewAccountDto;
import com.example.accountservice.models.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toEntity(CreateAccountDto createAccountDto);
    ViewAccountDto toViewAccountDto(Account account);
}
