package com.telusko.quizservice.controller;

import com.telusko.quizservice.model.dto.QuizDTO;
import com.telusko.quizservice.model.QuestionWrapper;
import com.telusko.quizservice.model.Response;
import com.telusko.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO) {
//        return new ResponseEntity<String>("I am here", HttpStatus.OK);
        return quizService.createQuiz(quizDTO.getCategory(), quizDTO.getNumQ(), quizDTO.getTitle());
    }

    @GetMapping("getQuiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id) {
        return quizService.getQuiz(id);
    }

    @PostMapping("submit")
    public ResponseEntity<Integer> submit(@RequestBody List<Response> responses) {
        return quizService.calculateResult(responses);
    }


}
