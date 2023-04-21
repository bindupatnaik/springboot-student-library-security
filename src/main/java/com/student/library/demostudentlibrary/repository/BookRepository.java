package com.student.library.demostudentlibrary.repository;

import com.student.library.demostudentlibrary.model.Book;
import com.student.library.demostudentlibrary.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("select b from Book b where b.author in (select id from Author a where a.name=:authorName)")
    List<Book> findBooksByAuthor(String authorName);

    @Query("select b from Book b where b.author in (select id from Author a where a.name=:authorName) and b.isAvailable=:available" )
    List<Book> findBooksByAuthorAndAvailableFlag(String authorName, boolean available);

    List<Book> findByBookName(String bookTitle);

    List<Book> findByGenre(Genre genre);

    List<Book> findByIsAvailable(boolean available);

    @Modifying
    @Transactional
    @Query("update Book b set b.bookName=:#{#book.bookName}," +
            "b.genre=:#{#book.genre}," +
            "b.isAvailable=:#{#book.isAvailable}," +
            "b.author=:#{#book.author} " +
            "where b.id=:#{#book.id}")
    void updateBook(Book book);

    @Modifying
    @Transactional
    void deleteByBookName(String name);
}
