package com.umut.ubank.service.impl;

import com.umut.ubank.model.Account;
import com.umut.ubank.repository.AccountRepository;
import com.umut.ubank.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(UUID id) {
        return findById(id);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccountById(UUID id) {
        Account account = findById(id);
        accountRepository.delete(account);
    }

    public Account updateAccount(UUID id, Account account) {
        Account a1 = findById(id);
        a1.setAmount(account.getAmount());
        a1.setBank(account.getBank());
        a1.setCustomer(account.getCustomer());

        return accountRepository.save(a1);
    }

    private Account findById(UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id "+id));
    }
}
