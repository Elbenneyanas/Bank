package org.bank.dao;

import org.bank.db.TransactionDB;
import org.bank.model.Transaction;

import java.util.List;

public class TransactionDAO {
  TransactionDB transactionDB;

  public TransactionDAO(TransactionDB transactionDB) {
    this.transactionDB = transactionDB;
  }

  public List<Transaction> getAllTransactionsByAccountNum(long accountNum) {
    return transactionDB.getAllTransactionsByAccountNum(accountNum);
  }

  public void makeTransaction(Transaction transaction) {
    transactionDB.makeTransaction(transaction);
  }

  public List<Transaction> getAllTransactions() {
    return transactionDB.getAllTransactions();
  }
}
