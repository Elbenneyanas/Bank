package org.bank.storage;

import org.bank.dao.TransactionDAO;
import org.bank.model.Transaction;

import java.util.List;

public class TransactionStorage {
  TransactionDAO transactionDAO;

  public TransactionStorage(TransactionDAO transactionDAO) {
    this.transactionDAO = transactionDAO;
  }

  public void makeTransaction(Transaction transaction) {
    transactionDAO.makeTransaction(transaction);
  }

  public List<Transaction> getAllTransactionsByAccountNum(long accountNum) {
    return transactionDAO.getAllTransactionsByAccountNum(accountNum);
  }

  public List<Transaction> getAllTransactions() {
    return transactionDAO.getAllTransactions();

  }
}
