package com.example.demo.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "equation")
public class Equation {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private Integer a;
    @NotNull
    private Integer b;
    @NotNull
    private Integer c;
    private String answer;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
