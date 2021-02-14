package org.bank.db;

import org.bank.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionDB {

  private List<Transaction> transactions = new ArrayList<>();

  private static long transactionId = 1;

  public List<Transaction> getAllTransactionsByAccountNum(long accountNum) {
    return transactions.stream().filter(transaction -> transaction.getAccountNum() == accountNum)
            .collect(Collectors.toList());
  }

  public void makeTransaction(Transaction transactionEntity) {
    transactionEntity.setId(transactionId);
    transactions.add(transactionEntity);
    transactionId++;
  }

  public List<Transaction> getAllTransactions() {
    return transactions;
  }
}
