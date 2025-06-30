package com.example.accountservice.services;

import com.example.accountservice.dto.CreateAccountDto;
import com.example.accountservice.dto.UpdateAccountDto;
import com.example.accountservice.dto.ViewAccountDto;
import com.example.accountservice.mapper.AccountMapper;
import com.example.accountservice.models.Account;
import com.example.accountservice.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public Account createAccount(CreateAccountDto createAccountDto) {
        Account account = accountMapper.toEntity(createAccountDto);
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountById(UUID id) {
        return accountRepository.findAccountById(id)
                .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "account with id: " + id + " doesn't exists")
        );
    }

    @Override
    public List<ViewAccountDto> getAllAccountsByHandlerId(String handlerId) {
        List<Account> accounts = accountRepository.findAllByHandlerId(handlerId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This handler doesn't have accounts")
                );

        return accounts.stream().map(accountMapper::toViewAccountDto).collect(Collectors.toList());
    }

    @Override
    public Account updateAccount(UpdateAccountDto updateAccount) {
        Account account = accountRepository.findAccountById(updateAccount.accountId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This handler doesn't have accounts")
        );
        account.setAccountName(updateAccount.accountName());
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccountById(UUID id) {
        accountRepository.deleteById(id);
    }
}
