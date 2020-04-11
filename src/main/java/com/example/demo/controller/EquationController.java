package com.example.demo.controller;

import com.example.demo.model.Equation;
import com.example.demo.model.User;
import com.example.demo.service.EquationService;
import com.example.demo.service.UserService;
import java.security.Principal;
import java.util.List;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EquationController {
    private final EquationService equationService;
    private final UserService userService;

    public EquationController(EquationService equationService, UserService userService) {
        this.equationService = equationService;
        this.userService = userService;
    }

    @GetMapping("/equation")
    @Secured("ROLE_USER")
    public String equation() {
        return "equation";
    }

    @PostMapping("/equation")
    @Secured("ROLE_USER")
    public String equationAnswer(@ModelAttribute Equation equation, Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        Equation equationWithAnswer=equationService.addEquation(equation, user);
        model.addAttribute("equation",equationWithAnswer);
        return "answer";
    }

    @GetMapping("/list")
    @Secured("ROLE_USER")
    public String list(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        List<Equation> equations=equationService.findUserEquation(user);
        model.addAttribute("equations",equations);
        return "list";
    }

    @GetMapping("/deleteEquation")
    @Secured("ROLE_USER")
    public String deleteEquationGet(@RequestParam Long id,Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());
        equationService.deleteEquationById(id);
        List<Equation> equations=equationService.findUserEquation(user);
        model.addAttribute("equations",equations);
        return "list";
    }
}
