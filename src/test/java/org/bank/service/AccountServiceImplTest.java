package org.bank.service;

import org.bank.model.User;
import org.junit.After;
import org.junit.Test;
import org.bank.model.Account;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class AccountServiceImplTest extends BaseBankServicesTest {


  public AccountServiceImplTest() {
  }

  @After
  public void tearDown() {
    purgeData();
  }

  @Test
  public void shouldCreateAccountWithInformation() {
    try {
      Account account = new Account();
      accountService.createAccount(account);
    } catch (IllegalArgumentException e) {
      // Expected
    }

    try {
      Account account = new Account();
      account.setId(1);
      accountService.createAccount(account);
    } catch (IllegalArgumentException e) {
      // Expected
    }

    //given
    Account account = new Account();
    Date created = new Date();
    account.setCreated(created);
    account.setBalance(0.0);

    //when
    Account createdAccount = accountService.createAccount(account);

    //then
    assertNotNull(createdAccount.getId());
    assertEquals(created, createdAccount.getCreated());
    assertEquals(0.0, createdAccount.getBalance(), .1);
  }


  @Test
  public void shouldGetAccountById() throws Exception {
    //given
    Account account = new Account();
    Date created = new Date();
    account.setCreated(created);
    account.setBalance(0.0);
    Account createdAccount = accountService.createAccount(account);
    long accountId = createdAccount.getId();

    //when
    Account storedAccount = accountService.getAccountById(accountId);

    //then
    assertNotNull(storedAccount);
    assertTrue(storedAccount.getId() > 0);
    assertEquals(created, createdAccount.getCreated());
    assertEquals(0.0, createdAccount.getBalance(), .1);
  }

  @Test
  public void shouldGetAccountByNum() throws Exception {
    //given
    Account account = new Account();
    Date created = new Date();
    account.setCreated(created);
    account.setAccountNum(12345678);
    account.setBalance(0.0);
    Account createdAccount = accountService.createAccount(account);

    try {
      accountService.getAccountByNum(0);
      fail();
    } catch (IllegalArgumentException e) {
      // Expected
    }

    //when
    long accountNum = createdAccount.getAccountNum();
    Account storedAccount = accountService.getAccountByNum(accountNum);

    //then
    assertNotNull(storedAccount);
    assertTrue(storedAccount.getId() > 0);
    assertEquals(accountNum, createdAccount.getAccountNum());
    assertEquals(created, createdAccount.getCreated());
    assertEquals(0.0, createdAccount.getBalance(), .1);
  }

  @Test
  public void shouldNotGetAccountWhenIdNotPositive() throws Exception {
    //when
    Account storedAccount = accountService.getAccountById(0);
    //then
    assertNull(storedAccount);
  }

  @Test
  public void shouldGetAllAccountsWhenExist() throws Exception {
    //given
    Account account = new Account();
    Date created = new Date();
    account.setCreated(created);
    account.setBalance(0.0);
    accountService.createAccount(account);
    Account account2 = new Account();
    Date created2 = new Date();
    account.setCreated(created2);
    account.setBalance(0.0);
    accountService.createAccount(account2);

    //when
    List<Account> accountList = accountService.getAllAccounts();

    //then
    assertEquals(accountList.size(), 2);
    assertTrue(accountList.contains(account));
    assertTrue(accountList.contains(account2));
  }

  @Test
  public void shouldUpdateAccountWithNewInformation() {
    //given
    Account account = new Account();
    Date created = new Date();
    account.setCreated(created);
    account.setBalance(0.0);
    Account createdAccount = accountService.createAccount(account);

    long accountId = createdAccount.getId();
    Account storedAccount = accountService.getAccountById(accountId);

    assertNotNull(storedAccount);
    assertTrue(storedAccount.getId() > 0);

    try {
      accountService.updateAccount(null);
      fail();
    } catch (IllegalArgumentException e) {
      // Expected
    }

    try {
      Account accountB = new Account();
      accountService.updateAccount(accountB);
      fail();
    } catch (IllegalArgumentException e) {
      // Expected
    }
    try {
      Account accountB = new Account();
      accountB.setId(0);
      accountService.updateAccount(accountB);
    } catch (IllegalArgumentException e) {
      // Expected
    }
    account.setBalance(1.5);

    //when
    accountService.updateAccount(account);

    //then
    Account updatedAccount = accountService.getAccountById(account.getId());
    assertEquals(1.5, updatedAccount.getBalance(), 0.1);
  }

  @Test
  public void shouldDeleteAccountById() throws Exception {
    //given
    Account account = new Account();
    Date created = new Date();
    account.setCreated(created);
    account.setBalance(0.0);
    Account createdAccount = accountService.createAccount(account);
    Account account2 = new Account();
    Date created2 = new Date();
    account2.setCreated(created2);
    account2.setBalance(0.0);
    accountService.createAccount(account2);
    Account account3 = new Account();
    Date created3 = new Date();
    account3.setCreated(created3);
    account3.setBalance(0.0);
    accountService.createAccount(account3);

    long accountId = createdAccount.getId();
    Account storedAccount = accountService.getAccountById(accountId);

    assertNotNull(storedAccount);
    assertTrue(storedAccount.getId() > 0);

    try {
      accountService.deleteAccountById(0);
      fail();
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      accountService.deleteAccountById(9999);
      fail();
    } catch (Exception e) {
      e.printStackTrace();
    }

    //when
    List<Account> accountList = accountService.getAllAccounts();
    int accountListLength = accountList.size();
    accountService.deleteAccountById(accountId);

    //then
    List<Account> newAccountList = accountService.getAllAccounts();
    int newAccountListLength = newAccountList.size();
    assertEquals(accountListLength - 1, newAccountListLength);
  }

  @Test
  public void shouldLinkAccountWithUser() throws Exception {
    //given
    Account account = new Account();
    Date created = new Date();
    account.setCreated(created);
    account.setBalance(0.0);
    Account createdAccount = accountService.createAccount(account);

    User user = new User();
    user.setFirstName("John");
    user.setLastName("Doe");
    Date birthday = new Date();
    user.setBirthday(birthday);
    user.setPhone("00000000");
    User createdUser = userService.createUser(user);

    try {
      accountService.linkAccountWithUser(0, 0);
      fail();
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      accountService.linkAccountWithUser(12345678, 0);
      fail();
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      account.setAccountNum(12345678);
      accountService.linkAccountWithUser(12345678, 0);
      fail();
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      account.setAccountNum(12345678);
      accountService.linkAccountWithUser(12345678, 9999);
      fail();
    } catch (Exception e) {
      e.printStackTrace();
    }

    //when
    accountService.linkAccountWithUser(createdAccount.getAccountNum(), createdUser.getId());

    //then
    Account updatedAccount = accountService.getAccountByNum(createdAccount.getAccountNum());
    assertNotNull(updatedAccount);
    assertTrue(updatedAccount.getUserId() > 0);
    assertEquals(updatedAccount.getUserId(), createdUser.getId());

    //try to link with linked account
    try {
      accountService.linkAccountWithUser(12345678, 9999);
      fail();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void shouldGetAllUserAccountsWhenExists() throws Exception {
    //given
    Account account = new Account();
    Date created = new Date();
    account.setCreated(created);
    account.setBalance(0.0);
    account.setAccountNum(1700);
    Account createdAccount = accountService.createAccount(account);

    Account account2 = new Account();
    Date created2 = new Date();
    account2.setCreated(created2);
    account2.setBalance(0.0);
    account2.setAccountNum(1701);
    Account createdAccount2 = accountService.createAccount(account2);

    Account account3 = new Account();
    Date created3 = new Date();
    account3.setCreated(created3);
    account3.setBalance(0.0);
    account3.setAccountNum(1701);
    Account createdAccount3 = accountService.createAccount(account3);

    User user = new User();
    user.setFirstName("John");
    user.setLastName("Doe");
    Date birthday = new Date();
    user.setBirthday(birthday);
    user.setPhone("00000000");
    User createdUser = userService.createUser(user);
    accountService.linkAccountWithUser(createdAccount.getAccountNum(), createdUser.getId());
    accountService.linkAccountWithUser(createdAccount2.getAccountNum(), createdUser.getId());

    try {
      accountService.getAllAccountsByUserId(0);
      fail();
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      accountService.getAllAccountsByUserId(99999);
      fail();
    } catch (Exception e) {
      e.printStackTrace();
    }

    //when
    List<Account> userAccounts = accountService.getAllAccountsByUserId(createdUser.getId());

    //then
    assertNotNull(userAccounts);
    assertEquals(2, userAccounts.size());
    userAccounts.forEach(acc -> {
      assertTrue(acc.getUserId() == createdUser.getId());
    });
  }

  @Test
  public void shouldGetSumOfAllUserAccountsBalance() throws Exception {
    //given
    Account account = new Account();
    Date created = new Date();
    account.setCreated(created);
    account.setBalance(12.2);
    account.setAccountNum(1700);
    Account createdAccount = accountService.createAccount(account);

    Account account2 = new Account();
    Date created2 = new Date();
    account2.setCreated(created2);
    account2.setBalance(25.3);
    account2.setAccountNum(1701);
    Account createdAccount2 = accountService.createAccount(account2);

    User user = new User();
    user.setFirstName("John");
    user.setLastName("Doe");
    Date birthday = new Date();
    user.setBirthday(birthday);
    user.setPhone("00000000");
    User createdUser = userService.createUser(user);
    accountService.linkAccountWithUser(createdAccount.getAccountNum(), createdUser.getId());
    accountService.linkAccountWithUser(createdAccount2.getAccountNum(), createdUser.getId());

    try {
      accountService.getAllAccountsBalanceByUserId(0);
      fail();
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      accountService.getAllAccountsBalanceByUserId(99999);
      fail();
    } catch (Exception e) {
      e.printStackTrace();
    }

    //when
    double balanceSum = accountService.getAllAccountsBalanceByUserId(createdUser.getId());

    //then
    assertNotNull(balanceSum);
    assertEquals(createdAccount.getBalance() + createdAccount2.getBalance(), balanceSum, .1);
  }

  private void purgeData() {
    accountService.getAllAccounts().clear();
  }

}
