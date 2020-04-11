package com.example.demo.service;

import com.example.demo.model.Equation;
import com.example.demo.model.User;
import java.util.List;

public interface EquationService {
    Equation addEquation(Equation equation, User user);

    List<Equation> findUserEquation(User user);

    void deleteEquationById(Long equationId);
}
