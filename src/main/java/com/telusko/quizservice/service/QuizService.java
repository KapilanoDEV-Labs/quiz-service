package com.telusko.quizservice.service;

import com.telusko.quizservice.dao.Quizdao;
import com.telusko.quizservice.feign.QuestionInterface;
import com.telusko.quizservice.model.QuestionWrapper;
import com.telusko.quizservice.model.Quiz;
import com.telusko.quizservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    Quizdao quizdao;

    @Autowired
    QuestionInterface questionInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        Quiz quiz = new Quiz();
        List<Integer> questionIds = questionInterface.getQuestionsForQuiz(category,numQ).getBody();

        quiz.setQuizTitle(title);
        quiz.setCategory(category);
        quiz.setQuestionIds(questionIds);
        quizdao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
        Optional<Quiz> quiz = quizdao.findById(id);
        List<Integer> questionIds = quiz.get().getQuestionIds();

        List<QuestionWrapper> questionWrapperList = questionInterface.getQuestionsFromId(questionIds).getBody();
        return new ResponseEntity<>(questionWrapperList, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(List<Response> responses) {
        Integer points=0;

        points = questionInterface.getScore(responses).getBody();
        return new ResponseEntity<>(points, HttpStatus.OK);
    }
}
