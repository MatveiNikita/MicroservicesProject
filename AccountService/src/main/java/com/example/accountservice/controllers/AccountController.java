package com.example.accountservice.controllers;

import com.example.accountservice.dto.ChangeAmountDto;
import com.example.accountservice.dto.CreateAccountDto;
import com.example.accountservice.dto.UpdateAccountDto;
import com.example.accountservice.dto.ViewAccountDto;
import com.example.accountservice.jwt.JwtService;
import com.example.accountservice.models.Account;
import com.example.accountservice.services.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/accounts", produces = {"application/json"})
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081/account-api")
public class AccountController {

    private final AccountService accountService;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody @Valid CreateAccountDto createAccountDto, @RequestHeader("Authorization") String token){
        jwtService.isTokenValid(token);
        String email = jwtService.extractUserEmail(token);
        Account newAccount = accountService.createAccount(createAccountDto, email);
        return ResponseEntity.ok(newAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@RequestParam("id") UUID id){
        Account account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/by-email")
    public ResponseEntity<List<ViewAccountDto>> getAccountsByUserEmail(@RequestHeader("Authorization") String token){
        jwtService.isTokenValid(token);
        List<ViewAccountDto> allAccounts = accountService.getAllAccountsByUserEmail(jwtService.extractUserEmail(token));
        return ResponseEntity.ok(allAccounts);
    }

    @PatchMapping
    public ResponseEntity<Account> updateAccount(@RequestBody UpdateAccountDto updateAccountDto){
        Account account = accountService.updateAccount(updateAccountDto);
        return ResponseEntity.ok(account);
    }

    @PatchMapping("/add-balance")
    public ResponseEntity<Account> addBalance(@Valid @RequestBody ChangeAmountDto changeAmountDto, @RequestHeader("Authorization") String token){
        Account account = accountService.addBalance(changeAmountDto.uuid(), changeAmountDto.amount(), token);
        return ResponseEntity.ok(account);
    }

    @PatchMapping("/minus-balance")
    public ResponseEntity<Account> minusBalance(@Valid @RequestBody ChangeAmountDto changeAmountDto, @RequestHeader("Authorization") String token){
        Account account = accountService.minusBalance(changeAmountDto.uuid(), changeAmountDto.amount(), token);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable("id") UUID id){
        accountService.deleteAccountById(id);
        return ResponseEntity.ok("deleted");
    }

}
