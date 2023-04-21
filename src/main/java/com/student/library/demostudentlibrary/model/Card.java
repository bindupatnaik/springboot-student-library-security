package com.student.library.demostudentlibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * https://stackoverflow.com/questions/2302802/how-to-fix-the-hibernate-object-references-an-unsaved-transient-instance-save
 * Exception: org.hibernate.TransientPropertyValueException: object references an unsaved transient instance - save the transient instance before flushing : com.student.library.demostudentlibrary.model.Card.student -> com.student.library.demostudentlibrary.model.Student
 * You should include cascade="all" (if using xml) or cascade=CascadeType.ALL (if using annotations) on your collection mapping.
 *
 * This happens because you have a collection in your entity, and that collection has one or more items which are not present in the database. By specifying the above options you tell hibernate to save them to the database when saving their parent.
 */

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(value=EnumType.STRING)
    private CardStatus cardStatus;
    @CreationTimestamp
    private java.util.Date createdOn;
    @UpdateTimestamp
    private java.util.Date updatedOn;
    @OneToOne(mappedBy = "card" ,  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("card")
    private Student student;

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("card")
    private List<Book> bookList;

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaction> transactionList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }



//    public List<Student> getStudents() {
//        return students;
//    }
//
//    public void setStudents(List<Student> students) {
//        this.students = students;
//    }
}
