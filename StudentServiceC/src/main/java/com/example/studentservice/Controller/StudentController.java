package com.example.studentservice.Controller;

import com.example.studentservice.Domain.Element;
import com.example.studentservice.Domain.Student;
import com.example.studentservice.Domain.Students;
import com.example.studentservice.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

//    @Autowired
//    RestTemplate restTemplate;

    @GetMapping("/")
    public String home() {
        return "Hello From service C";
    }

    @PutMapping("/student/{studentNumber}/{element}")
    public ResponseEntity<?> addElement(@PathVariable String studentNumber, @PathVariable String element) {
        int studentCheck =  studentService.AddElement(studentNumber, element);
        if(studentCheck == 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else if(studentCheck == 1) {
            return new ResponseEntity<String>("No student found with " + studentNumber + " id", HttpStatus.NOT_FOUND);

        } else if(studentCheck == 2) {
            return new ResponseEntity<String>("No Element found with " + element + " id", HttpStatus.NOT_FOUND);
        } else if(studentCheck == 3){
            return new ResponseEntity<String>("Not enough money to buy  " + element + " id", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
//
    }

    @PostMapping("/")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        studentService.createStudent(student);
        return new ResponseEntity<Student>(student, HttpStatus.CREATED);
    }

    @GetMapping("/students")
    public ResponseEntity<?> viewStudents(){
       Students schools = studentService.getAllStudents();
        if(schools == null) {
            return new ResponseEntity<String>("No students found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Students>(schools, HttpStatus.OK);
        }
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

    @GetMapping("/student/{studentNumber}")
    public ResponseEntity<?> viewStudent(@PathVariable String studentNumber) {
        Student student = studentService.getStudent(studentNumber);
        if(student == null) {
            return new ResponseEntity<String>("No student found with " + studentNumber + " id", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        }
    }

    @PostMapping("/student/{studentNumber}/changeScore/{score}")
    public ResponseEntity<?> addScore(@PathVariable String studentNumber, @PathVariable int score) {
        int studentCheck = studentService.AddScore(studentNumber, score);
        if (studentCheck == 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else if (studentCheck == 1) {
            return new ResponseEntity<String>("No student found with " + studentNumber + " id", HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(HttpStatus.OK);
        } }


//    @GetMapping("/elements")
//    public List<Element> viewElements() {
////        String clientName = restTemplate.getForObject("http://localhost:8761/eureka/apps/ELEMENTSERVICE", String.class);
////        System.out.println("Client name: " + clientName);
//
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<String>(headers);
//        ResponseEntity<List<Element>> response = restTemplate.exchange("http://localhost:8084/elements",HttpMethod.GET,entity, new ParameterizedTypeReference<List<Element>>() {});
//        return response.getBody(); //this returns List of Employee
//    }