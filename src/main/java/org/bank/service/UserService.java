package org.bank.service;

import org.bank.model.User;

import java.util.List;

public interface UserService {
  /**
   * create a new user with the given information
   *
   * @param user
   * @return {@link User}
   */
  User createUser(User user);

  /**
   * get a user by id
   *
   * @param userId
   * @return the found {@link User}
   */
  User getUserById(long userId);

  /**
   * update an existing user with the new information.
   *
   * @param userB
   */
  void updateUser(User userB);

  /**
   * get all created users
   *
   * @return List of {@link User}
   */
  List<User> getAllUsers();

  /**
   * delete an existing user by id
   *
   * @param userId
   * @throws Exception
   */
  void deleteUserById(long userId) throws Exception;
}
