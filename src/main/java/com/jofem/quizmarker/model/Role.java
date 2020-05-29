package com.jofem.quizmarker.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long role_id;
    private String role;

}
