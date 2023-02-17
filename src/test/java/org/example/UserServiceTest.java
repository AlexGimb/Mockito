package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    User viktor;
    User dmitriy;
    String CorrectName = "Viktor";
    String CorrectPassword = "123";
    String unCorrectName = "";
    String UnCorrectPassword = "";


    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;

    @BeforeEach
    public void setUp() {
        viktor = new User("Viktor", "123");
        dmitriy = new User("Dmitriy", "321");
        userRepository.addUser(viktor);
        userRepository.addUser(dmitriy);
    }

    @Test
    public void getUserList() {
        List<User> expected = userRepository.getAllUsers();
        List<User> actual = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void newUserIsEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.newUser(unCorrectName, UnCorrectPassword));
    }
    @Test
    public void newUserNotPassword() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.newUser(CorrectName, UnCorrectPassword));
    }
    @Test
    public void newUserNotLogin() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.newUser(unCorrectName, CorrectPassword));
    }
    @Test
    public void newUserIsAlready() {
        when(userRepository.getAllUsers()).thenReturn(List.of(viktor));
        Assertions.assertThrows(UserNonUniqueException.class, () -> userService.newUser("Viktor", "123"));
    }
}