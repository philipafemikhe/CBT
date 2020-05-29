package com.jofem.quizmarker.tenant.controller;

import com.jofem.quizmarker.tenant.model.Course;
import com.jofem.quizmarker.tenant.model.Question;
import com.jofem.quizmarker.tenant.service.CourseService;
import com.jofem.quizmarker.tenant.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/tenant/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/{id}")
    public Question question(@PathVariable Long id){
        return this.questionService.getQuestion(id);
    }

    @GetMapping("/all")
    public String getAllCourses(Model model){
        ArrayList<Question> questions = this.questionService.getAllQuestion();
        model.addAttribute("questions", questions);
        return "questions";
    }

    @PostMapping("/add")
    public String AddCourse(@RequestBody Question question){
        this.questionService.save(question);
        return "Record added succesfully";
    }

    @PutMapping("/update/{id}")
    public String updateCourse(@RequestBody Question question, @PathVariable Long id){
        this.questionService.save(question);
        return "Record updated successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable Long id){
        return this.questionService.deleteQuestionById(id);
    }
}
