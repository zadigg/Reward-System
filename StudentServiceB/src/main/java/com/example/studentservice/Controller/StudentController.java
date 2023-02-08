package com.example.studentservice.Controller;

import com.example.studentservice.Domain.Student;
import com.example.studentservice.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/")
    public String home() {
        return "Hello From service B";
    }

    @PutMapping("/student/{studentNumber}/element")
    public ResponseEntity<?> addElement(@PathVariable String studentNumber) {
        Boolean studentCheck =  studentService.AddElement(studentNumber);
        if(studentCheck) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No student found with " + studentNumber + " id", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        studentService.createStudent(student);
        return new ResponseEntity<Student>(student, HttpStatus.CREATED);
    }

    @GetMapping("/students")
    public ResponseEntity<?> viewStudents(){
        List<Student> schools = studentService.getAllStudents();
        if(schools.size()==0){
            return new ResponseEntity<String>("No Student Found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Student>>(schools, HttpStatus.OK);
    }

    @DeleteMapping("/student/{studentNumber}")
    public ResponseEntity<?> deleteStudent(@PathVariable String studentNumber) {
        Boolean studentCheck =  studentService.deleteStudent(studentNumber);
        if(studentCheck) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No student found with " + studentNumber + " id", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/student/{studentNumber}/edit")
    public ResponseEntity<?> updateStudent(@RequestBody Student student, @PathVariable String studentNumber) {
        Boolean checkStudent = studentService.updateStudent(student, studentNumber);
        if(checkStudent) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No student found with " + student.getStudentNumber() + " id", HttpStatus.NOT_FOUND);
        }
    }





    @DeleteMapping("/student/{studentNumber}/{elementName}")
    public ResponseEntity<?> removeElement( @PathVariable String studentNumber,@PathVariable String elementName ) {
        Boolean elementResult =  studentService.RemoveElement(studentNumber, elementName);
        if(elementResult) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No " + elementName + " element found", HttpStatus.NOT_FOUND);
        }

    }


}
