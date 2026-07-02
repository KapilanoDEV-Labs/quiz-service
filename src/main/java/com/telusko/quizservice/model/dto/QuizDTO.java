package com.telusko.quizservice.model.dto;

import lombok.Data;

@Data
public class QuizDTO {
    private String category;
    private int numQ;
    private String title;
}
