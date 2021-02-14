package org.bank.service;

import org.bank.model.Account;

import java.util.List;

public interface AccountService {
  /**
   * Create an account with the given information
   *
   * @param account
   * @return an {@link Account}
   */
  public Account createAccount(Account account);

  /**
   * get an account by id
   *
   * @param accountId
   * @return an {@link Account}
   */
  Account getAccountById(long accountId);

  /**
   * get an account by Num
   *
   * @param accountNum
   * @return an {@link Account}
   */
  Account getAccountByNum(long accountNum);

  /**
   * get all accounts created.
   *
   * @return List of {@link Account}
   */
  List<Account> getAllAccounts();

  /**
   * update an account with the given information.
   *
   * @param account
   */
  void updateAccount(Account account);

  /**
   * delete account by id
   *
   * @param accountId
   * @throws Exception
   */
  void deleteAccountById(long accountId) throws Exception;

  /**
   * link an account with a given user
   *
   * @param accountNum
   * @param userId
   * @throws Exception
   */
  void linkAccountWithUser(long accountNum, long userId) throws Exception;

  /**
   * search for all user's accounts
   *
   * @param userId
   * @return List of {@link Account}
   * @throws Exception
   */
  List<Account> getAllAccountsByUserId(long userId) throws Exception;

  /**
   * calculate all user's accounts balance
   *
   * @param userId
   * @return the wealth of the user
   * @throws Exception
   */
  double getAllAccountsBalanceByUserId(long userId) throws Exception;
}
