package com.student.library.demostudentlibrary.controller;

import com.student.library.demostudentlibrary.model.Student;
import com.student.library.demostudentlibrary.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity createStudent(@RequestBody() Student student){
        studentService.createStudent(student);
        return new ResponseEntity<>("Student is added successfully to the system", HttpStatus.CREATED);
    }

    @GetMapping("/getStudents")
    public ResponseEntity getStudents(){
        List<Student> studentsList = studentService.getStudents();
        return new ResponseEntity<>(studentsList, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getStudent")
    public ResponseEntity getStudent(@RequestParam("name") String name){
        Student student = studentService.getStudent(name);
        if (student==null){
            return new ResponseEntity("student is not present", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(student, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updateStudent(@RequestBody() Student student){
        studentService.updateStudent(student);
        return new ResponseEntity<>( "updated student info successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{name}")
    public ResponseEntity deleteStudent(@PathVariable(value = "name", required=true) String name){
        studentService.deleteStudent(name);
        return new ResponseEntity("student is deleted successfully from the system", HttpStatus.OK);
    }


}
