package com.example.studentservice.Service;

import com.example.studentservice.Domain.Student;
import com.example.studentservice.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;


    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    public Boolean deleteStudent(String studentNumber) {
        Optional<Student> student = studentRepository.findById(studentNumber);
        if(student.isPresent()) {
            studentRepository.delete(student.get());
            return true;
        } else {
            return false;
        }
    }

    public Boolean updateStudent(Student student, String studentNumber) {
        Optional<Student> student1 = studentRepository.findById(studentNumber);
        if(student1.isPresent()) {
            student1.get().setFirstName(student.getFirstName() );
            student1.get().setLastName(student.getLastName());
            student1.get().setScore(student.getScore());
            student1.get().setRewards(student.getRewards());
            student1.get().setClassOf(student.getClassOf());
            student1.get().setSchool(student.getSchool());
            studentRepository.save(student1.get());
            return true;
        } else {
            return false;
        }
    }


    public Boolean AddElement(String studentNumber) {

        Optional<Student> student = studentRepository.findById(studentNumber);
        if(student.isPresent() && student.get().getRewards().containsKey("Hair")) {
            HashMap<String, Integer> NewElements = new HashMap<>();
            NewElements.put("Hair", 40);
            NewElements.put("Eye",40  );
            NewElements.put("Ear", 41);

// 100 + 10 = 110 - 30 = 80
            //  90 - 30 = 60
            // 60 - 20 = 40
//            NewElements.put("Hair", 10);
//            NewElements.put("Eye", 10);

            HashMap<String, Integer> OldElements = student.get().getRewards();

            for(String key: NewElements.keySet()) {
                System.out.println(key);
                if(OldElements.containsKey(key)) {

                    // old elements -> Hair : 20
                    // 1000 + 20 -> 1020

                    //score 1000
                    // new element -> Hair : 30
                    // 1020 - 30 -> 990

//                    score + old element - new element -> 20 + 20 -30
                    if(student.get().getScore() + OldElements.get(key) - NewElements.get(key) >= 0 ){
                        student.get().setScore(student.get().getScore() + OldElements.get(key));
                        student.get().setScore(student.get().getScore() - NewElements.get(key));
                        OldElements.put(key, NewElements.get(key));
                    } else {
                        System.out.println("Not enough points");
                    }

                } else {
                    if(student.get().getScore() - NewElements.get(key) > 0 ) {
                        student.get().setScore(student.get().getScore() - NewElements.get(key));
                        OldElements.put(key, NewElements.get(key));
                    }
                }

                student.get().setRewards(OldElements);
                studentRepository.save(student.get());
            }
        }

        return student.isPresent();

    }

    public Boolean RemoveElement(String studentNumber, String elementName) {

        Optional<Student> student = studentRepository.findById(studentNumber);
        Boolean isElementPresent = student.get().getRewards().containsKey(elementName);
        HashMap<String, Integer> OldElements = student.get().getRewards();
        if(OldElements.containsKey(elementName)) {
            student.get().setScore(student.get().getScore() + OldElements.get(elementName));
        }
        OldElements.remove(elementName);
        student.get().setRewards(OldElements);
        studentRepository.save(student.get());

        return isElementPresent;
        }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}

