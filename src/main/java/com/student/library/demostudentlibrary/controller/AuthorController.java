package com.student.library.demostudentlibrary.controller;

import com.student.library.demostudentlibrary.model.Author;
import com.student.library.demostudentlibrary.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping(value = "/create")
    public ResponseEntity createAuthor(@RequestBody() Author author){
        authorService.createAuthor(author);
        return new ResponseEntity("author is created successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "/update")
    public ResponseEntity updateAuthor(@RequestBody() Author author){
        authorService.updateAuthor(author);
        return new ResponseEntity("author is updated ", HttpStatus.CREATED);
    }

    @GetMapping(value="/get")
    public ResponseEntity getAuthor(@RequestParam("name") String name){
        Author author = authorService.getAuthor(name);
        return new ResponseEntity(author, HttpStatus.OK);
    }

    @GetMapping(value="/getAll")
    public ResponseEntity getAuthor(){
        List<Author> authorList = authorService.getAuthors();
        return new ResponseEntity(authorList, HttpStatus.OK);
    }

}
