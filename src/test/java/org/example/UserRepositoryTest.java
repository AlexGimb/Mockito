package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    UserRepository userRepository;
    User viktor;
    User dmitriy;

    @Test
    public void emptyUserList() {
        List<User> actual = new ArrayList<>();
        userRepository.users.clear();
        assertEquals(userRepository.getAllUsers(), actual);
    }

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository();
        viktor = new User("Viktor", "123");
        dmitriy = new User("Dmitriy", "321");
        userRepository.addUser(viktor);
        userRepository.addUser(dmitriy);
    }

    @Test
    public void userList() {
        List<User> users = userRepository.getAllUsers();
        List<User> actual = new ArrayList<>();
        actual.add(viktor);
        actual.add(dmitriy);
        assertIterableEquals(users, actual);
    }

    @Test
    public void searchUser() {
        assertEquals(userRepository.searchUser("Viktor"), Optional.of(viktor));
    }

    @Test
    public void searchNotUser() {
        assertNotEquals(userRepository.searchUser("Dmitriy"), Optional.of(viktor));
    }

    @Test
    public void searchUserOfLoginAndPassword() {
        assertEquals(userRepository.searchUserOfLoginAndPassword("Dmitriy", "321"), Optional.of(dmitriy));
    }
    @Test
    public void searchUserOfNotEqualsPassword() {
        boolean condition = userRepository.users.contains(new User("Dmitriy", "000"));
        assertFalse(condition);
    }
    @Test
    public void searchUserNotEqualsLogin() {
        boolean condition = userRepository.users.contains(new User("Viktor", "321"));
        assertFalse(condition);
    }
}
