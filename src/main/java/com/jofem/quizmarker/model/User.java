package com.jofem.quizmarker.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    private String email;
    private String phone;

    @Column(name = "user_type")
    private String userType;

    private String status;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Timestamp createdAt;

    private Timestamp updatedAt;
//
//    @OneToOne
//    private License subscriber;

}
