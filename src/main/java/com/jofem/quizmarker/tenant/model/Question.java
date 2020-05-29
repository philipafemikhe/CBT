package com.jofem.quizmarker.tenant.model;

import javax.persistence.*;
import java.sql.Timestamp;


public class Question {
    private Long id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;
    private int marks;
    private int courseId;
    private int isAnswer;
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public Question() {
    }

    public Question(Long id, String question, String option1, String option2, String option3, String option4, String option5, int marks, int courseId, int isAnswer) {
        this.id = id;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.option5 = option5;
        this.marks = marks;
        this.courseId = courseId;
        this.isAnswer = isAnswer;
    }

    public Question(Long id, String question, String option1, String option2, String option3, String option4, String option5, int marks, int courseId, int isAnswer, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.option5 = option5;
        this.marks = marks;
        this.courseId = courseId;
        this.isAnswer = isAnswer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getIsAnswer() {
        return isAnswer;
    }

    public void setIsAnswer(int isAnswer) {
        this.isAnswer = isAnswer;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                ", option5='" + option5 + '\'' +
                ", marks=" + marks +
                ", courseId=" + courseId +
                ", isAnswer=" + isAnswer +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
