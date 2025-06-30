package com.example.accountservice.services;

import com.example.accountservice.dto.CreateAccountDto;
import com.example.accountservice.dto.UpdateAccountDto;
import com.example.accountservice.dto.ViewAccountDto;
import com.example.accountservice.models.Account;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    Account createAccount(CreateAccountDto createAccountDto);
    Account getAccountById(UUID id);
    List<ViewAccountDto> getAllAccountsByHandlerId(String handlerId);
    Account updateAccount(UpdateAccountDto updateAccount);
    void deleteAccountById(UUID id);
}
