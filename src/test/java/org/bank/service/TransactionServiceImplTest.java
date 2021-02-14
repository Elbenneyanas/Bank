package org.bank.service;

import org.bank.constant.TransactionType;
import org.bank.model.Account;
import org.bank.model.Transaction;
import org.junit.After;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TransactionServiceImplTest extends BaseBankServicesTest {

  public TransactionServiceImplTest() {
  }

  @After
  public void tearDown() {
    purgeData();
  }

  @Test
  public void shouldDepositMoneyWithExistingAccount() throws Exception {
    //given
    Account account = new Account();
    Date created = new Date();
    account.setCreated(created);
    double initBalance = 0;
    account.setBalance(initBalance);
    account.setAccountNum(12345678);
    account.setSecurityPass(1234);
    accountService.createAccount(account);
    Transaction transaction = new Transaction();

    try {
      transactionService.makeTransaction(null, account.getSecurityPass());
      fail();
    } catch (IllegalArgumentException e) {
      // Expected
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      transaction.setAccountNum(0);
      transactionService.makeTransaction(transaction, account.getSecurityPass());
      fail();
    } catch (IllegalArgumentException e) {
      // Expected
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      transaction.setAccountNum(1234567);
      transactionService.makeTransaction(transaction, account.getSecurityPass());
      fail();
    } catch (IllegalArgumentException e) {
      // Expected
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      transaction.setAccountNum(12345678);
      transactionService.makeTransaction(transaction, 12345);
      fail();
    } catch (IllegalArgumentException e) {
      // Expected
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      transaction.setAccountNum(12345678);
      transaction.setAmount(-150);
      transactionService.makeTransaction(transaction, account.getSecurityPass());
      fail();
    } catch (IllegalArgumentException e) {
      // Expected
    } catch (Exception e) {
      e.printStackTrace();
    }


    transaction.setAccountNum(account.getAccountNum());
    Date transCreated = new Date();
    transaction.setCreated(transCreated);
    transaction.setAmount(2.5);
    transaction.setType(TransactionType.DEPOSIT);

    //when
    transactionService.makeTransaction(transaction, account.getSecurityPass());
    Account affectedAccount = accountService.getAccountByNum(account.getAccountNum());

    //then
    double transactionMoney = transaction.getType() == TransactionType.DEPOSIT ? +transaction.getAmount() : -transaction.getAmount();
    assertNotNull(affectedAccount);
    assertEquals(affectedAccount.getBalance(), initBalance + transactionMoney, 0);
  }

  @Test
  public void shouldWithdrawMoneyWhenAccountExists() throws Exception {
    //given
    Account account = new Account();
    Date created = new Date();
    account.setCreated(created);
    double initBalance = 9.3;
    account.setBalance(initBalance);
    account.setAccountNum(12345678);
    account.setSecurityPass(1234);
    accountService.createAccount(account);
    Transaction transaction = new Transaction();
    transaction.setAccountNum(account.getAccountNum());
    Date transCreated = new Date();
    transaction.setCreated(transCreated);
    transaction.setAmount(5.5);
    transaction.setType(TransactionType.WITHDRAW);

    //when
    transactionService.makeTransaction(transaction, account.getSecurityPass());
    Account affectedAccount = accountService.getAccountByNum(account.getAccountNum());

    //then
    double transactionMoney = transaction.getType() == TransactionType.DEPOSIT ? +transaction.getAmount() : -transaction.getAmount();
    assertNotNull(affectedAccount);
    assertEquals(affectedAccount.getBalance(), initBalance + transactionMoney, 0);
  }

  @Test
  public void shouldGetAllTransactions() throws Exception {
    //given
    Account account = new Account();
    Date created = new Date();
    account.setCreated(created);
    double initBalance = 50;
    account.setBalance(initBalance);
    account.setAccountNum(12345678);
    account.setSecurityPass(1234);
    accountService.createAccount(account);

    Account account2 = new Account();
    Date created2 = new Date();
    account.setCreated(created2);
    account2.setBalance(50);
    account2.setAccountNum(7878);
    account2.setSecurityPass(1234);
    accountService.createAccount(account2);

    Transaction transaction = new Transaction();
    transaction.setAccountNum(account.getAccountNum());
    Date transCreated = new Date();
    transaction.setCreated(transCreated);
    transaction.setAmount(5.5);
    transaction.setType(TransactionType.WITHDRAW);

    Transaction transaction2 = new Transaction();
    transaction2.setAccountNum(account.getAccountNum());
    Date transCreated2 = new Date();
    transaction2.setCreated(transCreated2);
    transaction2.setAmount(2.1);
    transaction2.setType(TransactionType.WITHDRAW);

    Transaction transaction3 = new Transaction();
    transaction3.setAccountNum(account.getAccountNum());
    Date transCreated3 = new Date();
    transaction3.setCreated(transCreated3);
    transaction3.setAmount(23);
    transaction3.setType(TransactionType.DEPOSIT);

    Transaction transaction4 = new Transaction();
    transaction4.setAccountNum(7878);
    Date transCreated4 = new Date();
    transaction4.setCreated(transCreated4);
    transaction4.setAmount(23);
    transaction4.setType(TransactionType.DEPOSIT);

    try {
      transactionService.getAllTransactionsByAccountNum(0);
      fail();
    } catch (IllegalArgumentException e) {
      // Expected
    }

    //when
    transactionService.makeTransaction(transaction, account.getSecurityPass());
    transactionService.makeTransaction(transaction2, account.getSecurityPass());
    transactionService.makeTransaction(transaction3, account.getSecurityPass());
    transactionService.makeTransaction(transaction4, account2.getSecurityPass());
    List<Transaction> transactionList = transactionService.getAllTransactionsByAccountNum(account.getAccountNum());
    Account affectedAccount = accountService.getAccountByNum(account.getAccountNum());

    //then
    assertNotNull(transactionList);
    assertNotNull(affectedAccount);
    assertEquals(3, transactionList.size());
    double transactionsSum = transactionList.stream()
            .mapToDouble(transactionItem -> transactionItem.getType() == TransactionType.DEPOSIT ? +transactionItem.getAmount() : -transactionItem.getAmount())
            .sum();
    assertEquals(affectedAccount.getBalance(), initBalance + transactionsSum, 0);
  }

  private void purgeData() {
    transactionService.getAllTransactions().clear();
  }

}
