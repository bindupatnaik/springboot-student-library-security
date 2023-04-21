package com.student.library.demostudentlibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    @Column(unique = true)
    private String email;
    private String country;

//    @OneToOne(fetch = FetchType.LAZY)
    @OneToOne
    @JoinColumn //this table will have reference of card. so that tomorrow if student has to be removed there won't be any dependency in other tables since it acts as child.
    @JsonIgnoreProperties("student")
    private Card card;

    public Student(){

    }

    public Student(String name, int age, String country, String email){
        this.name = name;
        this.age = age;
        this.country = country;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }


}
