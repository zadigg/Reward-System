package com.example.studentservice.Controller;

import com.example.studentservice.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/")
    public String home() {
        return "Hello World";
    }



//    @PutMapping("/student/{studentNumber}")
//    public String EditStudent(@PathVariable String studentNumber) {
//        studentService.EditStudent(studentNumber);
//        return "Student Updated";
//    }

    @PostMapping("/")
    public String post() {
        studentService.createStudent();
        return "Student Created";
    }

    @PutMapping("/student/{studentNumber}")
    public String AddElement(@PathVariable String studentNumber) {
        studentService.AddElement(studentNumber);
        return "Element Bought";
    }

    @DeleteMapping("/student/{elementName}")
    public String RemoveElement(@PathVariable String elementName) {
        studentService.RemoveElement(elementName);
        return "Element Removed";
    }


}
