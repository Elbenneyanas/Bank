package org.bank.db;

import org.bank.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDB {

  private List<Account> accounts = new ArrayList<>();

  private static long accountId = 1;

  public Account createAccount(Account accountEntity) {
    accountEntity.setId(accountId);
    accounts.add(accountEntity);
    accountId++;
    return accountEntity;
  }

  public Account getAccountById(long accountId) {
    return accounts.stream().filter(account -> account.getId() == accountId)
            .findFirst().orElse(null);
  }

  public Account getAccountByNum(long accountNum) {
    return accounts.stream().filter(account -> account.getAccountNum() == accountNum)
            .findFirst().orElse(null);
  }

  public List<Account> getAllAccounts() {
    return accounts;
  }

  public void updateAccount(Account accountToUpdate) {
    accounts.stream()
            .filter(account -> account.getId() == accountToUpdate.getId())
            .findFirst()
            .ifPresent(account -> account = accountToUpdate);
  }

  public void deleteAccountById(long accountId) {
    accounts.removeIf(account -> account.getId() == accountId);
  }

  public List<Account> getAllAccountsByUserId(long userId) {
    return accounts.stream().filter(account -> account.getUserId() == userId)
            .collect(Collectors.toList());
  }
}
