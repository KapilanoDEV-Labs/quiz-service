package com.telusko.quizservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Entity
@Getter
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String quizTitle;
    private String category;

    @ElementCollection
    private List<Integer> questionIds;
}
