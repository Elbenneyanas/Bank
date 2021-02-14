package org.bank.storage;

import org.bank.dao.AccountDAO;
import org.bank.model.Account;

import java.util.List;

public class AccountStorage {
  AccountDAO accountDAO;

  public AccountStorage(AccountDAO accountDAO) {
    this.accountDAO = accountDAO;
  }

  public Account createAccount(Account accountToCreate) {
    return accountDAO.createAccount(accountToCreate);
  }

  public Account getAccountById(long accountId) {
    return accountDAO.getAccountById(accountId);
  }

  public List<Account> getAllAccounts() {
    return accountDAO.getAllAccounts();
  }

  public void updateAccount(Account account) {
    accountDAO.updateAccount(account);
  }

  public void deleteAccountById(long accountId) {
    accountDAO.deleteAccountById(accountId);
  }

  public Account getAccountByNum(long accountNum) {
    return accountDAO.getAccountByNum(accountNum);
  }

  public List<Account> getAllAccountsByUserId(long userId) {
    return accountDAO.getAllAccountsByUserId(userId);
  }
}
