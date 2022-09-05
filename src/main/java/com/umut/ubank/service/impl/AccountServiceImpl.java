package com.umut.ubank.service.impl;

import com.umut.ubank.exception.NotFoundException;
import com.umut.ubank.model.Account;
import com.umut.ubank.repository.AccountRepository;
import com.umut.ubank.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository
                .findAll()
                .stream()
                .filter(account -> account.getIsActive() == Boolean.TRUE)
                .collect(Collectors.toList());
    }

    public Account getAccountById(Long id) {
        Account account = findById(id);
        if(account.getIsActive() == Boolean.FALSE) {
            throw new NotFoundException("Account is not active!");
        }
        return account;
    }

    public void deleteAccountById(Long id) {
        Account account = findById(id);
        account.setIsActive(Boolean.FALSE);
        account.setCustomer(null);
        accountRepository.save(account);
    }

    public Account updateAccount(Long id, Account account) {
        Account a1 = findById(id);
        a1.setAmount(account.getAmount());
        a1.setBank(account.getBank());
        a1.setCustomer(account.getCustomer());
        a1.setIsActive(account.getIsActive());

        return accountRepository.save(a1);
    }

    private Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account not found with id "+id));
    }
}
