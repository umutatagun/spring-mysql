package com.umut.ubank.service;

import com.umut.ubank.model.Account;
import com.umut.ubank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface AccountService {

    List<Account> getAllAccounts();

    Account getAccountById(UUID id);

    Account createAccount(Account account);

    void deleteAccountById(UUID id);

    Account updateAccount(UUID id, Account account);
}
