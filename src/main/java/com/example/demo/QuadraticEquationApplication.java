package com.example.demo;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class QuadraticEquationApplication implements CommandLineRunner {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public QuadraticEquationApplication(PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(QuadraticEquationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setPassword(encoder.encode("1"));
        user.setUsername("user");
        List<Role> roles = new ArrayList<>();
        roles.add(Role.ROLE_USER);
        user.setRoles(roles);
        userRepository.save(user);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(encoder.encode("1"));
        roles.remove(Role.ROLE_USER);
        roles.add(Role.ROLE_ADMIN);
        admin.setRoles(roles);
        userRepository.save(admin);
    }
}
