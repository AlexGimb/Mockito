package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    public  List<User> users = new ArrayList<>();

    public List<User> getAllUsers() {
        return users.stream().toList();
    }

    public Optional <User> searchUser(String login) {
        return Optional.of(users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findAny()
                .orElseThrow());
    }

    public Optional <User> searchUserOfLoginAndPassword(String login, String password) {
        return Optional.of(users.stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findAny()
                .orElseThrow());
    }

    public void addUser(User user) {
        users.add(user);
    }

}
