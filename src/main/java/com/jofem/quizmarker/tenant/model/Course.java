package com.jofem.quizmarker.tenant.model;

import com.jofem.quizmarker.model.License;
import lombok.*;

import javax.persistence.*;

//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
public class Course{
    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int cu;
    private int passmark;
    private int classId;

    public Course() {
    }

    public Course(Long id, String title, String description, int cu, int passmark, int classId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.cu = cu;
        this.passmark = passmark;
        this.classId = classId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCu() {
        return cu;
    }

    public void setCu(int cu) {
        this.cu = cu;
    }

    public int getPassmark() {
        return passmark;
    }

    public void setPassmark(int passmark) {
        this.passmark = passmark;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", cu=" + cu +
                ", passmark=" + passmark +
                ", classId=" + classId +
                '}';
    }
}
