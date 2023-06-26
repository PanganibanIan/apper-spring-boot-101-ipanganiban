package com.apper.accountservice;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    public List<Account> accounts = new ArrayList<>();

    private final IdGeneratorService idGeneratorService;
    public AccountService(IdGeneratorService idGeneratorService) {
        this.idGeneratorService = idGeneratorService ;
    }


    public Account create(String firstName, String lastName, String userName, String clearPassword) {
        Account account = new Account() ;


        account.setId(idGeneratorService.getNextId());
        account.setBalance(1_000.0);

        LocalDateTime now = LocalDateTime.now();
        account.setCreationDate(now);
        account.setLastUpdated(now);

        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setUserName(userName);
        account.setClearPassword(clearPassword);
        account.setVerificationCode(idGeneratorService.generateRandomCharacters(6)) ;

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

    public Account update(String accountId, String firstName, String lastName, String email, String clearPassword) {
        for (Account account : accounts) {
            if (account.getId().equals(accountId)) {
                LocalDateTime now = LocalDateTime.now();
                account.setLastUpdated(now);
                account.setFirstName(firstName);
                account.setLastName(lastName);
                account.setUserName(email);
                account.setClearPassword(clearPassword);

                return account;
            }
        }
        return null;
    }

    public void delete(String accountId) {
        int ctr = 0;
        for (Account account : accounts) {
            if (account.getId().equals(accountId)) {
                accounts.remove(ctr);
            }
            ctr++;
        }
    }
}
