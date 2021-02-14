package org.bank.model;

import java.util.Date;

public class Account {
  private long id;
  private long accountNum;
  private Date created;
  private double balance;
  private int securityPass;
  private long userId;

  public Account() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getAccountNum() {
    return accountNum;
  }

  public void setAccountNum(long accountNum) {
    this.accountNum = accountNum;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public int getSecurityPass() {
    return securityPass;
  }

  public void setSecurityPass(int securityPass) {
    this.securityPass = securityPass;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }
}
