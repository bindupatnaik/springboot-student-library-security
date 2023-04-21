package com.student.library.demostudentlibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
//first create author and we provide author id in while creating the book.
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private String country;
    @Column(unique=true)
    private String email;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value={"author","card"})
    private List<Book> booksList;

    public Author(){

    }
    public Author(String name, int age, String country, String email){
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<Book> booksList) {
        this.booksList = booksList;
    }
}