package com.student.library.demostudentlibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String transactionId = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("bookList")
    private Card card;//card is parent , transaction has foreign key constraint.

    @ManyToOne
    @JoinColumn
    private Book book;//book is parent , transaction has foreign key constraint.
    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean isIssueOperation;

    @CreationTimestamp
    private Date transactionDate;

    private int fineAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public boolean isIssueOperation() {
        return isIssueOperation;
    }

    public void setIssueOperation(boolean issueOperation) {
        isIssueOperation = issueOperation;
    }

    public int getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(int fineAmount) {
        this.fineAmount = fineAmount;
    }
    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionId='" + transactionId + '\'' +
                ", card=" + card +
                ", book=" + book +
                ", transactionStatus=" + transactionStatus +
                ", isIssueOperation=" + isIssueOperation +
                ", transactionDate=" + transactionDate +
                ", fineAmount=" + fineAmount +
                '}';
    }
}
