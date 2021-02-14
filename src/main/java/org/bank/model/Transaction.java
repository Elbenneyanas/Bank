package org.bank.model;

import org.bank.constant.TransactionType;

import java.util.Date;

public class Transaction {
  private long id;
  private Date created;
  private TransactionType type;
  private double amount;
  private long accountNum;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public TransactionType getType() {
    return type;
  }

  public void setType(TransactionType type) {
    this.type = type;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public long getAccountNum() {
    return accountNum;
  }

  public void setAccountNum(long accountNum) {
    this.accountNum = accountNum;
  }
}
