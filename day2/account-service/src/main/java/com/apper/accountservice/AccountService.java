package com.apper.accountservice;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    public List<Account> accounts = new ArrayList<>();

    public Account create(String firstName, String lastName, String userName, String clearPassword) {
        Account account = new Account() ;

        String id = UUID.randomUUID().toString();
        System.out.println("Generated id: " + id);

        account.setId(id);
        account.setBalance(1_000.0);

        LocalDateTime now = LocalDateTime.now();
        account.setCreationDate(now);
        account.setLastUpdated(now);

        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setUserName(userName);
        account.setClearPassword(clearPassword);
        account.setVerificationCode("QW345T") ;

        accounts.add(account);

        return account;
    }

    public Account get(String accountId) {
        for (Account account : accounts) {
            if (account.getId().equals(accountId)) {
                return account ;
            }
        }
        return null;
    }

    public List<Account> getAll() {
        return accounts ;
    }
//
//    public void update() {
//
//    }
//
//    public void delete() {
//
//    }
}
