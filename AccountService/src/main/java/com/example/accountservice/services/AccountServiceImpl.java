package com.example.accountservice.services;

import com.example.accountservice.dto.CreateAccountDto;
import com.example.accountservice.dto.UpdateAccountDto;
import com.example.accountservice.dto.ViewAccountDto;
import com.example.accountservice.jwt.JwtService;
import com.example.accountservice.mapper.AccountMapper;
import com.example.accountservice.models.Account;
import com.example.accountservice.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final JwtService jwtService;
    @Override
    public Account createAccount(CreateAccountDto createAccountDto, String email) {
        Account account = new Account();
        account.setAccountName(createAccountDto.accountName());
        account.setUserEmail(email);
        System.out.println(account);
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
    public List<ViewAccountDto> getAllAccountsByUserEmail(String email) {
        return accountRepository.findAllAccountsByUserEmail(email)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Current User doesn't have accounts")
                ).stream()
                .map(accountMapper::toViewAccountDto).toList();
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

    @Override
    public Account addBalance(UUID id, BigDecimal add, String token) {
        jwtService.isTokenValid(token);
        String email = jwtService.extractUserEmail(token);
        Account account = accountRepository.findAccountByIdAndUserEmail(id, email)
                .orElseThrow(() -> new NoSuchElementException("Account doesn't"));
        account.setBalance(account.getBalance().add(add));
        return accountRepository.save(account);
    }

    @Override
    public Account minusBalance(UUID id, BigDecimal minusMoney,String token) {
        jwtService.isTokenValid(token);
        String email = jwtService.extractUserEmail(token);
        Account account = accountRepository.findAccountByIdAndUserEmail(id, email)
                .orElseThrow(() -> new NoSuchElementException("Account doesn't exist"));
        BigDecimal amount = account.getBalance();
        if (amount.compareTo(minusMoney) < 0) {
            throw new IllegalArgumentException("YOU DONT HAVE ENOUGH MONEY");
        }
        account.setBalance(amount.subtract(minusMoney));
        return accountRepository.save(account);
    }
}
