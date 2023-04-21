package com.student.library.demostudentlibrary.repository;

import com.student.library.demostudentlibrary.model.Transaction;
import com.student.library.demostudentlibrary.model.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository <Transaction, Integer>{
    @Query("select t from Transaction t where t.card.id=:cardId and t.book.id=:bookId and t.isIssueOperation=:isIssueOperation and t.transactionStatus=:status")
    List<Transaction> find(int cardId, int bookId, boolean isIssueOperation, TransactionStatus status);

//    List<Transaction> findByCard(int cardId);

    List<Transaction> findByCardId(int cardId);
}
