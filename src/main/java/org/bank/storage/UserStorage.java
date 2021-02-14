package org.bank.storage;

import org.bank.dao.UserDAO;
import org.bank.model.User;

import java.util.List;

public class UserStorage {
  UserDAO userDAO;

  public UserStorage(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  public User createUser(User userEntity) {
    return userDAO.createUser(userEntity);
  }

  public User getUserById(long userId) {
    return userDAO.getUserById(userId);
  }

  public List<User> getAllUsers() {
    return userDAO.getAllUsers();
  }

  public void updateUser(User user) {
    userDAO.updateUser(user);
  }

  public void deleteUserById(long userId) {
    userDAO.deleteUserById(userId);
  }
}
