package com.example.accountservice.services;

import com.example.accountservice.dto.CreateAccountDto;
import com.example.accountservice.dto.UpdateAccountDto;
import com.example.accountservice.dto.ViewAccountDto;
import com.example.accountservice.models.Account;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AccountService {

    Account createAccount(CreateAccountDto createAccountDto, String email);
    Account getAccountById(UUID id);
    List<ViewAccountDto> getAllAccountsByUserEmail(String email);
    Account updateAccount(UpdateAccountDto updateAccount);
    void deleteAccountById(UUID id);
    Account addBalance(UUID id, BigDecimal addMoney, String token);
    Account minusBalance(UUID uuid, BigDecimal minusMoney, String token);
}
