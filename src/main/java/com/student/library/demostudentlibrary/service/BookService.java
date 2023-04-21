package com.student.library.demostudentlibrary.service;

import com.student.library.demostudentlibrary.model.Book;
import com.student.library.demostudentlibrary.model.Genre;
import com.student.library.demostudentlibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    public void createBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> findBooksByAuthor(String authorName) {
        return bookRepository.findBooksByAuthor(authorName);
    }

    public List<Book> findBooksByAuthorAndAvailableFlag(String authorName, boolean available) {
        return bookRepository.findBooksByAuthorAndAvailableFlag(authorName, available);
    }

    public List<Book> findBooksByTitle(String bookTitle) {
        return bookRepository.findByBookName(bookTitle);
    }

    public List<Book> findBooksByGenre(String genre) {
        return bookRepository.findByGenre(Genre.valueOf(genre));
    }
//
//    public List<Book> findAllAvailableBooks(boolean available) {
//        return bookRepository.findByIsAvailable(available);
//    }

    public List<Book> getBooks(String authorName, boolean available, String bookTitle, String genre) {
        List<Book> bookList = null;
        if (authorName != null && (available == true || available == false)) {
            bookList = bookRepository.findBooksByAuthorAndAvailableFlag(authorName, available);
        } else if (authorName != null) {
            bookList = bookRepository.findBooksByAuthor(authorName);
        } else if (bookTitle!=null) {
            bookList = bookRepository.findByBookName(bookTitle);
        }else if (genre!=null){
            bookList = bookRepository.findByGenre(Genre.valueOf(genre));
        }else{
            bookList = bookRepository.findByIsAvailable(available);
        }
        return bookList;
    }

    public void updateBook(Book book) {
        bookRepository.updateBook(book);
    }
//when trying to delete a book which is already issued and there is transaction in transaction table it is not allowing to
    //delete records from book table throwing below exception.
    //java.sql.SQLIntegrityConstraintViolationException: Cannot delete or update a parent row: a foreign key constraint fails (`librarynew`.`transaction`, CONSTRAINT `FK8hddvclv2iqa3sg1dm8295pqw` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`))
    public void deleteBook(String name) {
        bookRepository.deleteByBookName(name);
    }
}
