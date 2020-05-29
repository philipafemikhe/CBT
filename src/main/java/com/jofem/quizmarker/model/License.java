package com.jofem.quizmarker.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user_license")
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "subscriber_name")
    private  String subscriberName;
    private  String address;
    private  String email;
    private  String phone;
    private String logo;
    private String status;

    @Column(name = "database_name")
    private String databaseName;

    @Column(name = "rc_number")
    private String rcNumber;

    @Column(name = "about_company")
    private String aboutCompany;

    private String website;
    private String state;
    private String country;

    @Column(name = "date_incorporated")
    private String dateIncorporated;

    @Column(name = "no_of_staff")
    private Long noOfStaff;

    @Column(name = "expiry_date")
    private String expiryDate;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Timestamp createdAt;

    private Timestamp updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;


    public License(String subscriberName, String address, String email, String phone) {
        this.subscriberName = subscriberName;
        this.address = address;
        this.email = email;
        this.phone = phone;

    }

    public License(String subscriberName, String address, String email, String phone, String logo, String status, String databaseName, String rcNumber, String aboutCompany, String website, String state, String country, String dateIncorporated, Long noOfStaff, Timestamp createdAt, Timestamp updatedAt, User user) {
        this.subscriberName = subscriberName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.logo = logo;
        this.status = status;
        this.databaseName = databaseName;
        this.rcNumber = rcNumber;
        this.aboutCompany = aboutCompany;
        this.website = website;
        this.state = state;
        this.country = country;
        this.dateIncorporated = dateIncorporated;
        this.noOfStaff = noOfStaff;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }

    public License(String subscriberName, String address, String email, String phone, String logo, String status, String databaseName, String rcNumber, String aboutCompany, String website, String state, String country, String dateIncorporated, Long noOfStaff, Timestamp createdAt, Timestamp updatedAt, User user, Subscription subscription) {
        this.subscriberName = subscriberName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.logo = logo;
        this.status = status;
        this.databaseName = databaseName;
        this.rcNumber = rcNumber;
        this.aboutCompany = aboutCompany;
        this.website = website;
        this.state = state;
        this.country = country;
        this.dateIncorporated = dateIncorporated;
        this.noOfStaff = noOfStaff;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
        this.subscription = subscription;
    }

    public License(String subscriberName, String address, String email, String phone, String logo, String status, String databaseName, String rcNumber, String aboutCompany, String website, String state, String country, String dateIncorporated, Long noOfStaff, String expiryDate, Timestamp createdAt, Timestamp updatedAt, User user, Subscription subscription) {
        this.subscriberName = subscriberName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.logo = logo;
        this.status = status;
        this.databaseName = databaseName;
        this.rcNumber = rcNumber;
        this.aboutCompany = aboutCompany;
        this.website = website;
        this.state = state;
        this.country = country;
        this.dateIncorporated = dateIncorporated;
        this.noOfStaff = noOfStaff;
        this.expiryDate = expiryDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
        this.subscription = subscription;
    }

    public License() {
    }
}
