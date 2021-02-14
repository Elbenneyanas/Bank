package org.bank.service;

import org.junit.After;
import org.junit.Test;
import org.bank.model.User;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

public class UserServiceImplTest extends BaseBankServicesTest {

  public UserServiceImplTest() {
  }

  @After
  public void tearDown() {
    purgeData();
  }

  @Test
  public void shouldCreateUserWithInformation() {
    try {
      User user = new User();
      userService.createUser(user);
    } catch (IllegalArgumentException e) {
      // Expected
    }

    try {
      User user = new User();
      user.setId(1);
      userService.createUser(user);
    } catch (IllegalArgumentException e) {
      // Expected
    }

    try {
      User user = new User();
      user.setFirstName("John");
      userService.createUser(user);
    } catch (IllegalArgumentException e) {
      // Expected
    }

    try {
      User user = new User();
      user.setFirstName("John");
      user.setLastName("Doe");
      userService.createUser(user);
    } catch (IllegalArgumentException e) {
      // Expected
    }

    try {
      User user = new User();
      user.setFirstName("John");
      user.setLastName("Doe");
      user.setPhone("00000000");
      userService.createUser(user);
    } catch (IllegalArgumentException e) {
      // Expected
    }

    try {
      User user = new User();
      user.setFirstName("John");
      user.setLastName("Doe");
      user.setBirthday(new Date());
      userService.createUser(user);
    } catch (IllegalArgumentException e) {
      // Expected
    }
    //given
    User user = new User();
    user.setFirstName("John");
    user.setLastName("Doe");
    Date birthday = new Date();
    user.setBirthday(birthday);
    user.setPhone("00000000");
    user.setAddress("Street 2");

    //when
    User createdUser = userService.createUser(user);

    //then
    assertNotNull(createdUser.getId());
    assertEquals("John", createdUser.getFirstName());
    assertEquals("Doe", createdUser.getLastName());
    assertEquals(birthday, createdUser.getBirthday());
    assertEquals("00000000", createdUser.getPhone());
    assertEquals("Street 2", createdUser.getAddress());
  }

  @Test
  public void shouldGetUserById() throws Exception {
    //given
    User user = new User();
    user.setFirstName("John");
    user.setLastName("Doe");
    Date birthday = new Date();
    user.setBirthday(birthday);
    user.setPhone("00000000");
    User createdUser = userService.createUser(user);
    long userId = createdUser.getId();

    //when
    User storedUser = userService.getUserById(userId);

    //then
    assertNotNull(storedUser);
    assertTrue(storedUser.getId() > 0);
    assertEquals("John", createdUser.getFirstName());
    assertEquals("Doe", createdUser.getLastName());
    assertEquals(birthday, createdUser.getBirthday());
    assertEquals("00000000", createdUser.getPhone());
  }

  @Test
  public void shouldNotGetUserWhenIdNotPositive() throws Exception {
    //when
    User storedUser = userService.getUserById(0);
    //then
    assertNull(storedUser);
  }

  @Test
  public void shouldGetAllUsersWhenExist() throws Exception {
    //given
    User user = new User();
    user.setFirstName("John");
    user.setLastName("Doe");
    Date birthday = new Date();
    user.setBirthday(birthday);
    user.setPhone("00000000");
    userService.createUser(user);
    User user2 = new User();
    user2.setFirstName("John");
    user2.setLastName("Doe");
    Date birthday2 = new Date();
    user2.setBirthday(birthday2);
    user2.setPhone("00000000");
    userService.createUser(user2);

    //when
    List<User> userList = userService.getAllUsers();

    //then
    assertEquals(userList.size(), 2);
    assertTrue(userList.contains(user));
    assertTrue(userList.contains(user2));
  }

  @Test
  public void shouldUpdateUserWithNewInformation() {
    //given
    User user = new User();
    user.setFirstName("John");
    user.setLastName("Doe");
    Date birthday = new Date();
    user.setBirthday(birthday);
    user.setPhone("00000000");
    User createdUser = userService.createUser(user);

    long userId = createdUser.getId();
    User storedUser = userService.getUserById(userId);

    assertNotNull(storedUser);
    assertTrue(storedUser.getId() > 0);

    try {
      userService.updateUser(null);
      fail();
    } catch (IllegalArgumentException e) {
      // Expected
    }

    try {
      User userB = new User();
      userService.updateUser(userB);
      fail();
    } catch (IllegalArgumentException e) {
      // Expected
    }

    try {
      User userB = new User();
      userB.setId(4848);
      userService.updateUser(userB);
      fail();
    } catch (IllegalArgumentException e) {
      // Expected
    }

    try {
      User userB = new User();
      userB.setId(4848);
      userB.setFirstName("John");
      userService.updateUser(userB);
    } catch (IllegalArgumentException e) {
      // Expected
    }

    try {
      User userB = new User();
      userB.setId(4848);
      userB.setFirstName("John");
      userB.setLastName("Doe");
      userService.updateUser(userB);
    } catch (IllegalArgumentException e) {
      // Expected
    }

    try {
      User userB = new User();
      userB.setId(4848);
      userB.setFirstName("John");
      userB.setLastName("Doe");
      userB.setPhone("00000000");
      userService.updateUser(userB);
    } catch (IllegalArgumentException e) {
      // Expected
    }

    try {
      User userB = new User();
      userB.setId(4848);
      userB.setFirstName("John");
      userB.setLastName("Doe");
      userB.setBirthday(new Date());
      userService.updateUser(userB);
    } catch (IllegalArgumentException e) {
      // Expected
    }
    user.setFirstName("Dave");

    //when
    userService.updateUser(user);

    //then
    User updatedUser = userService.getUserById(user.getId());
    assertEquals("Dave", updatedUser.getFirstName());
  }

  @Test
  public void shouldDeleteUserById() throws Exception {
    //given
    User user = new User();
    user.setFirstName("John");
    user.setLastName("Doe");
    Date birthday = new Date();
    user.setBirthday(birthday);
    user.setPhone("00000000");
    User createdUser = userService.createUser(user);

    long userId = createdUser.getId();
    User storedUser = userService.getUserById(userId);

    assertNotNull(storedUser);
    assertTrue(storedUser.getId() > 0);

    try {
      userService.deleteUserById(0);
      fail();
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      userService.deleteUserById(9999);
      fail();
    } catch (Exception e) {
      e.printStackTrace();
    }

    //when
    List<User> userList = userService.getAllUsers();
    int userListLength = userList.size();
    userService.deleteUserById(userId);

    //then
    List<User> newUserList = userService.getAllUsers();
    int newUserListLength = userList.size();
    assertEquals(userListLength - 1, newUserListLength);
  }

  private void purgeData() {
    userService.getAllUsers().clear();
  }

}
