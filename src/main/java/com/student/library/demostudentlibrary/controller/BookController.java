package com.student.library.demostudentlibrary.controller;

import com.student.library.demostudentlibrary.model.Book;
import com.student.library.demostudentlibrary.service.BookService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping(value = "/create")
    public ResponseEntity createBook(@RequestBody() Book book) {
        bookService.createBook(book);
        return new ResponseEntity("book is created successfully", HttpStatus.CREATED);
    }

    @GetMapping(value = "/getBooksByAuthor/{name}")
    public ResponseEntity getBooksByAuthor(@PathVariable("name") String authorName) {
        List<Book> bookList = bookService.findBooksByAuthor(authorName);
        return new ResponseEntity(bookList, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/getBooksByAuthorAndAvailableFlag")
    public ResponseEntity getBooksByAuthorAndAvailableFlag(@RequestParam("authorName") String authorName, @RequestParam("available") boolean available) {
        List<Book> bookList = bookService.findBooksByAuthorAndAvailableFlag(authorName, available);
        return new ResponseEntity(bookList, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "getBooksByTitle")
    public ResponseEntity getBookByTitle(@RequestParam("title") String bookTitle) {
        List<Book> bookList = bookService.findBooksByTitle(bookTitle);
        return new ResponseEntity(bookList, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "getBooksByGenre")
    public ResponseEntity getBooksByGenre(@RequestParam("genre") String genre) {
        List<Book> bookList = bookService.findBooksByGenre(genre);
        return new ResponseEntity(bookList, HttpStatus.ACCEPTED);
    }

//    this will create a problem as there are multiple combinations of author, title, available, etc., and if the user gives different combinations the api will fail
    @GetMapping("/getBooks")
    public ResponseEntity getBooks(@RequestParam(value = "authorName", required = false) String authorName,
                                   @RequestParam(value = "available", required = false, defaultValue = "true" ) boolean available,
                                   @RequestParam(value = "title", required = false) String bookTitle,
                                   @RequestParam(value = "genre", required = false) String genre) {
        List<Book> bookList = bookService.getBooks(authorName, available, bookTitle, genre);
        return new ResponseEntity(bookList, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public ResponseEntity updateBook(@RequestBody Book book){
        bookService.updateBook(book);
        return new ResponseEntity("updated book details successfully", HttpStatus.OK);
    }

    //this api maynot work all the times if there is record in transaction table which violates foreign key constraint.
    @DeleteMapping("/delete/{name}")
    public ResponseEntity deleteBook(@PathVariable("name") String name){
        bookService.deleteBook(name);
        return new ResponseEntity("deleted book/books successfully", HttpStatus.OK);
    }

}
