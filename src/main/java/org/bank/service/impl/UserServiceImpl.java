package org.bank.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.bank.model.User;
import org.bank.service.UserService;
import org.bank.storage.UserStorage;

import java.util.List;

public class UserServiceImpl implements UserService {

  UserStorage userStorage;

  public UserServiceImpl(UserStorage userStorage) {
    this.userStorage = userStorage;
  }

  @Override
  public User createUser(User userToCreate) {
    if (userToCreate.getId() > 0) {
      throw new IllegalArgumentException("User id must be null");
    }
    if (StringUtils.isEmpty(userToCreate.getFirstName())) {
      throw new IllegalArgumentException("User first name must not be empty");
    }
    if (StringUtils.isEmpty(userToCreate.getLastName())) {
      throw new IllegalArgumentException("User last name must not be empty");
    }
    if (userToCreate.getBirthday() == null) {
      throw new IllegalArgumentException("User birthday must not be null");
    }
    if (StringUtils.isEmpty(userToCreate.getPhone())) {
      throw new IllegalArgumentException("User phone number must not be empty");
    }
    User createdUser = userStorage.createUser(userToCreate);
    return createdUser;
  }

  @Override
  public User getUserById(long userId) {
    User user = userStorage.getUserById(userId);
    if (user == null) {
      return null;
    }
    return user;
  }

  @Override
  public void updateUser(User user) {
    if (user == null) {
      throw new IllegalArgumentException("User must not be null");
    }
    if (user.getId() <= 0) {
      throw new IllegalArgumentException("User id must be positive");
    }
    if (StringUtils.isEmpty(user.getFirstName())) {
      throw new IllegalArgumentException("User first name must not be empty");
    }
    if (StringUtils.isEmpty(user.getLastName())) {
      throw new IllegalArgumentException("User last name must not be empty");
    }
    if (user.getBirthday() == null) {
      throw new IllegalArgumentException("User birthday must not be null");
    }
    if (StringUtils.isEmpty(user.getPhone())) {
      throw new IllegalArgumentException("User phone number must not be empty");
    }
    userStorage.updateUser(user);
  }

  @Override
  public List<User> getAllUsers() {
    return userStorage.getAllUsers();
  }

  @Override
  public void deleteUserById(long userId) throws Exception {
    if (userId <= 0) {
      throw new IllegalArgumentException("User id must be positive");
    }
    User user = userStorage.getUserById(userId);
    if (user == null) {
      throw new Exception("user with id " + userId + " is not found");
    }
    userStorage.deleteUserById(userId);
  }
}
