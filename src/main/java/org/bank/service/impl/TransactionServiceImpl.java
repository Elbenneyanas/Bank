package org.bank.service.impl;

import org.bank.constant.TransactionType;
import org.bank.model.Account;
import org.bank.model.Transaction;
import org.bank.service.AccountService;
import org.bank.service.TransactionService;
import org.bank.storage.TransactionStorage;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {

  AccountService accountService;

  TransactionStorage transactionStorage;

  public TransactionServiceImpl(AccountService accountService, TransactionStorage transactionStorage) {
    this.accountService = accountService;
    this.transactionStorage = transactionStorage;
  }

  @Override
  public void makeTransaction(Transaction transaction, int securityPass) throws Exception {
    if (transaction == null) {
      throw new IllegalArgumentException("Transaction must not be null");
    }

    long accountNum = transaction.getAccountNum();
    if (accountNum <= 0) {
      throw new IllegalArgumentException("Transaction Num must be positive");
    }

    Account account = accountService.getAccountByNum(accountNum);
    if (account == null) {
      throw new Exception("account with Num " + accountNum + " is not found");
    }

    if (account.getSecurityPass() != securityPass) {
      throw new Exception("Wrong security pass, please try again");
    }

    if (transaction.getAmount() < 0) {
      throw new Exception("Money to be deposited must be positive");
    }

    double amount = transaction.getType() == TransactionType.DEPOSIT ? +transaction.getAmount() : -transaction.getAmount();
    account.setBalance(account.getBalance() + amount);
    accountService.updateAccount(account);
    transactionStorage.makeTransaction(transaction);
  }

  @Override
  public List<Transaction> getAllTransactionsByAccountNum(long accountNum) {
    if (accountNum <= 0) {
      throw new IllegalArgumentException("account Num must be positive");
    }
    return transactionStorage.getAllTransactionsByAccountNum(accountNum);
  }

  @Override
  public List<Transaction> getAllTransactions() {
    return transactionStorage.getAllTransactions();
  }

}
