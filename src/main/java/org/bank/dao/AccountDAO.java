package org.bank.dao;

import org.bank.db.AccountDB;
import org.bank.model.Account;

import java.util.List;

public class AccountDAO {
  AccountDB accountDB;

  public AccountDAO(AccountDB accountDB) {
    this.accountDB = accountDB;
  }

  public Account createAccount(Account accountToCreate) {
    return accountDB.createAccount(accountToCreate);
  }

  public Account getAccountById(long accountId) {
    return accountDB.getAccountById(accountId);
  }

  public List<Account> getAllAccounts() {
    return accountDB.getAllAccounts();
  }

  public void updateAccount(Account account) {
    accountDB.updateAccount(account);
  }

  public void deleteAccountById(long accountId) {
    accountDB.deleteAccountById(accountId);
  }

  public Account getAccountByNum(long accountNum) {
    return accountDB.getAccountByNum(accountNum);
  }

  public List<Account> getAllAccountsByUserId(long userId) {
    return accountDB.getAllAccountsByUserId(userId);
  }
}
