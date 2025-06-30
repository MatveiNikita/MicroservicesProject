package com.example.accountservice.repositories;

import com.example.accountservice.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.accessibility.AccessibleAction;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findAccountById(UUID id);
    Optional<Account> findAccountByUserEmail(String handlerEmail);
    Optional<List<Account>> findAllAccountsByUserEmail(String handlerEmail);
    void deleteById(UUID id);
}
