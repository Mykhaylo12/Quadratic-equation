package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    boolean isUserPresent(String email);

    List<User> findAllUsers();

    void deleteUser(Long id);
}
