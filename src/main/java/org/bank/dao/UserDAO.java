package org.bank.dao;

import org.bank.db.UserDB;
import org.bank.model.User;

import java.util.List;

public class UserDAO {
  UserDB userDb;

  public UserDAO(UserDB userDb) {
    this.userDb = userDb;
  }

  public User createUser(User userEntity) {
    return userDb.createUser(userEntity);
  }

  public User getUserById(long userId) {
    return userDb.getUserById(userId);
  }

  public List<User> getAllUsers() {
    return userDb.getAllUsers();
  }

  public void updateUser(User user) {
    userDb.updateUser(user);
  }

  public void deleteUserById(long userId) {
    userDb.deleteUserById(userId);
  }
}
