package com.example.accountservice.controllers;

import com.example.accountservice.dto.CreateAccountDto;
import com.example.accountservice.dto.UpdateAccountDto;
import com.example.accountservice.dto.ViewAccountDto;
import com.example.accountservice.models.Account;
import com.example.accountservice.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/accounts", produces = {"application/json"})
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost/account-api")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody CreateAccountDto createAccountDto){
        Account newAccount = accountService.createAccount(createAccountDto);
        return ResponseEntity.ok(newAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@RequestParam("id") UUID id){
        Account account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{handler-id}")
    public ResponseEntity<List<ViewAccountDto>> getAccountsByHandlerId(@RequestParam("handler-id") String handlerId){
        List<ViewAccountDto> allAccounts = accountService.getAllAccountsByHandlerId(handlerId);
        return ResponseEntity.ok(allAccounts);
    }

    @PatchMapping
    public ResponseEntity<Account> updateAccount(@RequestBody UpdateAccountDto updateAccountDto){
        Account account = accountService.updateAccount(updateAccountDto);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountById(@RequestParam("id") UUID id){
        accountService.deleteAccountById(id);
        return ResponseEntity.ok("deleted");
    }
}
