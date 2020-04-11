package com.example.demo.repository;

import com.example.demo.model.Equation;
import com.example.demo.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquationRepository extends JpaRepository<Equation, Long> {
    List<Equation> findByUser(User user);
}
