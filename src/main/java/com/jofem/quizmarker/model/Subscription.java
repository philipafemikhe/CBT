package com.jofem.quizmarker.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "subscription")
public class  Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "package_name")
    private String packageName;
    private String description;

    @Column(name = "no_of_users")
    private Long noOfUsers;
    private String price;
    private String duration;


    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Timestamp createdAt;

    private Timestamp updatedAt;
}
