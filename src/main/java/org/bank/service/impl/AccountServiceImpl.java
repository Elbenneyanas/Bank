package org.bank.service.impl;

import org.bank.model.Account;
import org.bank.model.User;
import org.bank.service.AccountService;
import org.bank.service.UserService;
import org.bank.storage.AccountStorage;

import java.util.Date;
import java.util.List;

public class AccountServiceImpl implements AccountService {

  AccountStorage accountStorage;

  UserService userService;

  public AccountServiceImpl(AccountStorage accountStorage, UserService userService) {
    this.accountStorage = accountStorage;
    this.userService = userService;
  }

  @Override
  public Account createAccount(Account accountToCreate) {
    if (accountToCreate.getId() > 0) {
      throw new IllegalArgumentException("Account id must be null");
    }
    if (accountToCreate.getCreated() == null) {
      accountToCreate.setCreated(new Date());
    }

    Account createdAccount = accountStorage.createAccount(accountToCreate);
    return createdAccount;
  }

  @Override
  public Account getAccountById(long accountId) {
    Account account = accountStorage.getAccountById(accountId);
    if (account == null) {
      return null;
    }
    return account;
  }

  @Override
  public Account getAccountByNum(long accountNum) {
    if (accountNum <= 0) {
      throw new IllegalArgumentException("Account Num must be positive");
    }
    Account account = accountStorage.getAccountByNum(accountNum);
    if (account == null) {
      return null;
    }
    return account;
  }

  @Override
  public List<Account> getAllAccounts() {
    return accountStorage.getAllAccounts();
  }

  @Override
  public void updateAccount(Account account) {
    if (account == null) {
      throw new IllegalArgumentException("Account must not be null");
    }
    if (account.getId() <= 0) {
      throw new IllegalArgumentException("Account id must be positive");
    }
    accountStorage.updateAccount(account);
  }

  @Override
  public void deleteAccountById(long accountId) throws Exception {
    if (accountId <= 0) {
      throw new IllegalArgumentException("Account id must be positive");
    }
    Account account = accountStorage.getAccountById(accountId);
    if (account == null) {
      throw new Exception("account with id " + accountId + " is not found");
    }
    accountStorage.deleteAccountById(accountId);
  }

  @Override
  public void linkAccountWithUser(long accountNum, long userId) throws Exception {
    if (accountNum <= 0) {
      throw new IllegalArgumentException("Account Num must be positive");
    }
    Account account = accountStorage.getAccountByNum(accountNum);
    if (account == null) {
      throw new Exception("account with number " + accountNum + " is not found");
    }
    if (account.getUserId() > 0) {
      throw new Exception("account with id " + accountNum + " is already linked to another user");
    }
    User user = userService.getUserById(userId);
    if (user == null) {
      throw new Exception("user with id " + userId + " is not found");
    }
    account.setUserId(userId);
    accountStorage.updateAccount(account);
  }

  @Override
  public List<Account> getAllAccountsByUserId(long userId) throws Exception {
    if (userId <= 0) {
      throw new Exception("User id must be positive");
    }
    User user = userService.getUserById(userId);
    if (user == null) {
      throw new Exception("user with id " + userId + " is not found");
    }
    return accountStorage.getAllAccountsByUserId(userId);
  }

  @Override
  public double getAllAccountsBalanceByUserId(long userId) throws Exception {
    if (userId <= 0) {
      throw new Exception("User id must be positive");
    }
    User user = userService.getUserById(userId);
    if (user == null) {
      throw new Exception("user with id " + userId + " is not found");
    }
    List<Account> userAccounts = getAllAccountsByUserId(userId);
    return userAccounts.stream()
            .mapToDouble(Account::getBalance)
            .sum();
  }
}
