package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    UserRepository userRepository = new UserRepository();

    public List<String> getAllUsers() {
        return userRepository.users.stream()
                .map(User::getLogin)
                .collect(Collectors.toList());
    }

    public void newUser(String login, String password) {
        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("");
        }
        if (getAllUsers().contains(login)) {
            throw new UserNonUniqueException();
        }
        else {
            userRepository.addUser(new User(login, password));
        }
    }
}
