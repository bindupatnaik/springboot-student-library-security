package com.student.library.demostudentlibrary.service;

import com.student.library.demostudentlibrary.model.Author;
import com.student.library.demostudentlibrary.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public void createAuthor(Author author) {
        authorRepository.save(author);
    }

    public void updateAuthor(Author author) {
        authorRepository.updateAuthor(author);
    }

    public Author getAuthor(String name) {
        return authorRepository.findByName(name);
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }
}
