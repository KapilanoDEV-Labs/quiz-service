package com.telusko.quizservice.dao;

import com.telusko.quizservice.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface Quizdao extends JpaRepository<Quiz,Integer> {
//    @Query(value = "SELECT * FROM question q WHERE q.id IN (SELECT * FROM quiz_question_list qz WHERE qz.id=:id)",nativeQuery = true)
//    Optional<Question> findQuestionsById(int id);
}
