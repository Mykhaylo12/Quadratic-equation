package com.example.demo.service;

import com.example.demo.model.Equation;
import com.example.demo.model.User;
import com.example.demo.repository.EquationRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EquationServiceImpl implements EquationService {
    private final EquationRepository equationRepository;

    public EquationServiceImpl(EquationRepository equationRepository) {
        this.equationRepository = equationRepository;
    }

    @Override
    public Equation addEquation(Equation equation, User user) {
        int a = equation.getA();
        int b = equation.getB();
        int c = equation.getC();
        int d = b * b - 4 * a * c;
        if (d < 0) {
            equation.setAnswer("The equation has no right roots");
        } else if (d == 0) {
            int x1 = -b / 2 * a;
            equation.setAnswer(x1 + " ; " + x1);
        } else {
            int x1 = (int) ((-b - Math.sqrt(d)) / 2 * a);
            int x2 = (int) ((-b + Math.sqrt(d)) / 2 * a);
            equation.setAnswer(x1 + " ; " + x2);
        }
        equation.setUser(user);
        Equation equationWithAnswer = equationRepository.save(equation);
        return equationWithAnswer;
    }

    @Override
    public List<Equation> findUserEquation(User user) {
        return equationRepository.findByUser(user);
    }

    @Override
    public void deleteEquationById(Long equationId) {
        equationRepository.deleteById(equationId);
    }
}
