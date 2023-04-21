package com.student.library.demostudentlibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String bookName;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean isAvailable;
    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("bookList")
    private Card card;

//    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Transaction> transactionList;
    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("booksList")
    private Author author;

    public Book(){

    }

    public Book(String name, Genre genre, boolean available, Author author ){
        this.bookName = name;
        this.genre = genre;
        this.isAvailable = available;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean available) {
        isAvailable = available;
    }
}
