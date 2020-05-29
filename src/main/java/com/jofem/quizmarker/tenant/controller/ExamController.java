package com.jofem.quizmarker.tenant.controller;

import com.jofem.quizmarker.tenant.model.Question;
import com.jofem.quizmarker.tenant.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping("/start")
    public Question getStarted(){
        return nextQuestion();
    }

    public Question nextQuestion(){
        return examService.getNextQuestion();
    }

    @GetMapping("/next")
    public Question getNextQuestion(){
        return nextQuestion();
    }

}
