package com.student.library.demostudentlibrary.service;

import com.student.library.demostudentlibrary.model.*;
import com.student.library.demostudentlibrary.repository.BookRepository;
import com.student.library.demostudentlibrary.repository.CardRepository;
import com.student.library.demostudentlibrary.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Value("${books.max_limit}")
    private int books_limit;

    @Value("${books.max_allowed_days}")
    private int books_max_allowed_days;

    @Value("${books.fine_amount}")
    private int fine_amount;

    public String issueBook(int cardId, int bookId) {
        //if bookId or cardId are invalid finById method is throwing No Value Present exception.
        //we don't need to handle null case below it is auto handled.
        Card card = cardRepository.findById(cardId).get();
        Book book = bookRepository.findById(bookId).get();
        Transaction transaction = new Transaction();
        transaction.setCard(card);
        transaction.setBook(book);
        transaction.setIssueOperation(true);

        if (card == null || card.getCardStatus().equals(CardStatus.DEACTIVATED)) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            return "Card is invalid";
        }

        if (book == null || !book.getIsAvailable()) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            return "Book is not present or bookId is invalid";
        }

        if (card.getBookList().size() >= books_limit) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            return "Book limit has exceeded for this card";
        }
        book.setCard(card);
        book.setIsAvailable(false);
        List<Book> bookList = card.getBookList();
        bookList.add(book);
        card.setBookList(bookList);
        bookRepository.save(book);
        cardRepository.save(card);
        transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
        transactionRepository.save(transaction);

        return transaction.getTransactionId();
    }

    public String returnBook(int cardId, int bookId) throws Exception {

        //ideally bookId and cardId are given by frontEnd UI as user scans the card and returns the books that he has. he don't return the books that doesn't belong to him
        //but just for testing purpose we are doing below validations to make sure if user provides invalid combination.
        Book book = bookRepository.findById(bookId).get();
        Card card = cardRepository.findById(cardId).get();

        Transaction transaction = new Transaction();
        transaction.setCard(card);
        transaction.setBook(book);
        transaction.setIssueOperation(false);

        if (card == null || card.getCardStatus().equals(CardStatus.DEACTIVATED)) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setFineAmount(0);
            transactionRepository.save(transaction);
            return "Card is invalid";
        }

        if ( !book.getIsAvailable() && transaction.isIssueOperation()) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setFineAmount(0);
            transactionRepository.save(transaction);
            return "Book is not present or bookId is invalid";
        }

        List<Book> bookList = card.getBookList();
        boolean remove = bookList.remove(book);
        if (remove == false){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setFineAmount(0);
            transactionRepository.save(transaction);
            return "there is no book linked to this card";
        }

        card.setBookList(bookList);
        List<Transaction> trs = transactionRepository.find(cardId, bookId, true, TransactionStatus.SUCCESSFUL);
        Date transactionDate = trs.get(trs.size()-1).getTransactionDate();
        Date currentDate = new Date();

        long dateBeforeInMs = transactionDate.getTime();
        long dateAfterInMs = currentDate.getTime();

        long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);
        long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
        long total_fine_amount = 0;
        if (daysDiff > books_max_allowed_days){
            total_fine_amount = (daysDiff - books_max_allowed_days) * fine_amount ;
        }

        transaction.setFineAmount((int)total_fine_amount);
        transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
        book.setIsAvailable(true);
        book.setCard(null);
        bookRepository.save(book);
        cardRepository.save(card);
        transactionRepository.save(transaction);
        return transaction.getTransactionId();


    }
    public List<Transaction> getTransactions(int cardId) {
        return transactionRepository.findByCardId(cardId);
    }
}
