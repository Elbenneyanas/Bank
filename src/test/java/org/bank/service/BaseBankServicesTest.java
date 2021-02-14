package org.bank.service;

import org.bank.dao.AccountDAO;
import org.bank.dao.TransactionDAO;
import org.bank.dao.UserDAO;
import org.bank.db.AccountDB;
import org.bank.db.TransactionDB;
import org.bank.db.UserDB;
import org.bank.service.impl.AccountServiceImpl;
import org.bank.service.impl.TransactionServiceImpl;
import org.bank.service.impl.UserServiceImpl;
import org.bank.storage.AccountStorage;
import org.bank.storage.TransactionStorage;
import org.bank.storage.UserStorage;
import org.junit.Before;

public abstract class BaseBankServicesTest {

  protected UserDAO userDAO;

  protected UserDB userDB;

  protected UserStorage userStorage;

  protected UserService userService;

  protected AccountDAO accountDAO;

  protected AccountDB accountDB;

  protected TransactionDAO transactionDAO;

  protected TransactionDB transactionDB;

  protected AccountStorage accountStorage;

  protected TransactionStorage transactionStorage;

  protected AccountService accountService;

  protected TransactionService transactionService;


  @Before
  public void setUp() {
    userDB = new UserDB();
    userDAO = new UserDAO(userDB);
    userStorage = new UserStorage(userDAO);
    userService = new UserServiceImpl(userStorage);

    accountDB = new AccountDB();
    accountDAO = new AccountDAO(accountDB);
    accountStorage = new AccountStorage(accountDAO);
    accountService = new AccountServiceImpl(accountStorage, userService);

    transactionDB = new TransactionDB();
    transactionDAO = new TransactionDAO(transactionDB);
    transactionStorage = new TransactionStorage(transactionDAO);
    transactionService = new TransactionServiceImpl(accountService, transactionStorage);
  }


}
