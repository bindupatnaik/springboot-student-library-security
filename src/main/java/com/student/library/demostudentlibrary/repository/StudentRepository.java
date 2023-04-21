package com.student.library.demostudentlibrary.repository;

import com.student.library.demostudentlibrary.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByName(String name);
    
    @Modifying
    @Query("update Student s set s.name=:#{#student.name}, " +
            "s.email=:#{#student.email}, " +
            "s.age=:#{#student.age}, " +
            "s.country=:#{#student.country} " +
            "where s.id=:#{#student.id}")
    void updateStudent(Student student);

    //you don't need to write Query if you want to remove based on class properties. there will be functions like
    //deleteByName, deleteByAge, deleteByEmail etc.,
    void deleteByName(String name);

    @Modifying
    @Query("delete from Student s where s.name=:name")
    void deleteCustom(String name);
}
