package com.example.studentfeign.controller;

import com.example.studentfeign.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentFeignClient studentClient;
    @GetMapping("/")
    public String getName() {
        return studentClient.getName();
    }

    @PutMapping("/student/{studentNumber}/{element}")
    public ResponseEntity<?> addElement(@PathVariable String studentNumber, @PathVariable String element) {
        return studentClient.addElement(studentNumber, element);
    }

    @GetMapping("/students")
    public ResponseEntity<?> viewStudents() {
        return studentClient.viewStudents();
    }

    @PutMapping("/student/{studentNumber}/edit")
    public ResponseEntity<?> updateStudent(@RequestBody Student student, @PathVariable String studentNumber) {
        return studentClient.updateStudent(student, studentNumber);
    }

    @PostMapping("/")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return studentClient.addStudent(student);
    }

    @DeleteMapping("/student/{studentNumber}")
    public ResponseEntity<?> deleteStudent(@PathVariable String studentNumber) {
        return studentClient.deleteStudent(studentNumber);

    }

    @DeleteMapping("/student/{studentNumber}/{elementName}")
    public ResponseEntity<?> deleteElement(@PathVariable String studentNumber, @PathVariable String elementName) {
        return studentClient.deleteElement(studentNumber, elementName);
    }

    @GetMapping("/elements")
    public ResponseEntity<?> viewElements() {
        return studentClient.viewElements();
    }

    @GetMapping("/student/{studentNumber}")
    public ResponseEntity<?> viewStudent(@PathVariable String studentNumber) {
        return studentClient.viewStudent(studentNumber);
    }



    @FeignClient(name = "STUDENTSERVICE")
    public interface StudentFeignClient {
        @GetMapping("/")
        public String getName();

        @PutMapping("/student/{studentNumber}/{element}")
        public ResponseEntity<?> addElement(@PathVariable String studentNumber , @PathVariable String element);

        @GetMapping("/students")
        public ResponseEntity<?> viewStudents();

        @PutMapping("/student/{studentNumber}/element")
        public ResponseEntity<?> updateStudent(@RequestBody Student student, @PathVariable String studentNumber);


        @PostMapping("/")
        public ResponseEntity<Student> addStudent(@RequestBody Student student);

        @DeleteMapping("/student/{studentNumber}")
        public ResponseEntity<?> deleteStudent(@PathVariable String studentNumber);

        @DeleteMapping("/student/{studentNumber}/{elementName}")
        public ResponseEntity<?> deleteElement(@PathVariable String studentNumber, @PathVariable String elementName);

        @GetMapping("/elements")
        public ResponseEntity<?> viewElements();

        @GetMapping("/student/{studentNumber}")
        public ResponseEntity<?> viewStudent(@PathVariable String studentNumber);

    }
}
