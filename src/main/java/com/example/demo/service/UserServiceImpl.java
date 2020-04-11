package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;

import com.example.demo.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;


    public UserServiceImpl(PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(Role.ROLE_USER);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean isUserPresent(String email) {
        return false;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
