package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import java.util.List;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/adminPage")
    @Secured("ROLE_ADMIN")
    public String list(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "adminPage";
    }

    @GetMapping("/deleteUser")
    @Secured("ROLE_ADMIN")
    public String list(@RequestParam Long id, Model model) {
        userService.deleteUser(id);
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "adminPage";
    }
}
