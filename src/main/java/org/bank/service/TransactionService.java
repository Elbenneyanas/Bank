package org.bank.service;

import org.bank.model.Transaction;

import java.util.List;

public interface TransactionService {

  /**
   * Check with security pass and make a transaction with
   * the given information(type, amount,...).
   *
   * @param transaction
   * @param securityPass
   * @throws Exception
   */
  void makeTransaction(Transaction transaction, int securityPass) throws Exception;

  /**
   * return all transactions for a given accountNum
   *
   * @param accountNum
   * @return List of {@link Transaction}
   */
  List<Transaction> getAllTransactionsByAccountNum(long accountNum);

  /**
   * return all transactions
   *
   * @return List of {@link Transaction}
   */
  List<Transaction> getAllTransactions();
}
