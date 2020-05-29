package com.jofem.quizmarker.tenant.controller;



import com.jofem.quizmarker.tenant.model.Course;
import com.jofem.quizmarker.tenant.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tenant")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/course/{id}")
//    public Course getCourse(@PathVariable Long id){
//        return this.courseService.getCourse(id);
//    }
//    public String[] getCourse(@PathVariable Long id){
//        return this.courseService.getCourse(id);
//    }
    public Course course(@PathVariable Long id){
        return this.courseService.getCourse(id);
    }

    @GetMapping("/courses")

    public String getAllCourses(Model model){
        List<Course> courseList = this.courseService.getAllCourse();
        model.addAttribute("courseList", courseList);
        return "course_list";
    }

    @PostMapping("/course/add")
    public String AddCourse(@RequestBody Course course){
        this.courseService.save(course);
        return "Record added succesfully";
    }

    @PutMapping("/course/update/{id}")
    public String updateCourse(@RequestBody Course course, @PathVariable Long id){
        this.courseService.save(course);
        return "Record updated successfully";
    }

    @DeleteMapping("/course/delete/{id}")
    public String deleteCourse(@PathVariable Long id){
        return this.courseService.deleteCourseById(id);
    }
}
