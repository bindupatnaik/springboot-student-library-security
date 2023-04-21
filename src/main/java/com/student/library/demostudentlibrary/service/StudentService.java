package com.student.library.demostudentlibrary.service;

import com.student.library.demostudentlibrary.model.Card;
import com.student.library.demostudentlibrary.model.CardStatus;
import com.student.library.demostudentlibrary.model.Student;
import com.student.library.demostudentlibrary.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService {
    @Autowired
    CardService cardService;
    @Autowired
    StudentRepository studentRepository;

    public void createStudent(Student student) {
        Card card = cardService.createCard(student);
        student.setCard(card);
        studentRepository.save(student);
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(String name) {
        return studentRepository.findByName(name);
    }

    public void updateStudent(Student student) {
        studentRepository.updateStudent(student);
    }

    public void deleteStudent(String name) {
        cardService.deactivateCard(name, CardStatus.DEACTIVATED);
//        studentRepository.deleteByName(name);
        studentRepository.deleteCustom(name);
    }
}
