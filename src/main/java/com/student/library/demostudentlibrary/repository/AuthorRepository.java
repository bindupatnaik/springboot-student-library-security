package com.student.library.demostudentlibrary.repository;

import com.student.library.demostudentlibrary.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    @Modifying
    @Query("update Author a set a.name=:#{#author.name}, " +
            "a.email=:#{#author.email}, " +
            "a.age=:#{#author.age}, " +
            "a.country=:#{#author.country} " +
            "where a.id=:#{#author.id}")
    void updateAuthor(Author author);

    Author findByName(String name);
}
