package org.bank.db;

import org.bank.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDB {

  private List<User> users = new ArrayList<>();

  private static long userId = 1;

  public User createUser(User userEntity) {
    userEntity.setId(userId);
    users.add(userEntity);
    userId++;
    return userEntity;
  }

  public User getUserById(long userId) {
    return users.stream().filter(user -> user.getId() == userId)
            .findFirst().orElse(null);
  }

  public List<User> getAllUsers() {
    return users;
  }

  public void updateUser(User userToUpdate) {
    users.stream()
            .filter(user -> user.getId() == userToUpdate.getId())
            .findFirst()
            .ifPresent(user -> user = userToUpdate);
  }

  public void deleteUserById(long userId) {
    users.removeIf(user -> user.getId() == userId);
  }
}
