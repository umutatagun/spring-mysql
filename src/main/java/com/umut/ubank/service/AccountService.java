package com.umut.ubank.service;

import com.umut.ubank.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    List<Account> getAllAccounts();

    Account getAccountById(Long id);

    void deleteAccountById(Long id);

    Account updateAccount(Long id, Account account);
}
