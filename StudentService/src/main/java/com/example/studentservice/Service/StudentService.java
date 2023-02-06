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


    public void createStudent() {
//        List<String> rewards = Arrays.asList("Ear", "Eye");
        HashMap<String, Integer> NewElements = new HashMap<>();
        NewElements.put("Hair", 10);
        NewElements.put("Eye", 10);
        HashMap<String, Integer> rewards = NewElements;
        studentRepository.save(new Student("Abel", "Negash", "645182", "MIU", "2022", 1000,rewards));
    }

    public void EditStudent(String studentNumber) {
        Optional<Student> student = studentRepository.findById(studentNumber);
        if(student.isPresent()) {
            Student student1 = student.get();
            student1.setScore(1000);

        }
    }


    public void AddElement(String studentNumber) {
        Optional<Student> student = studentRepository.findById(studentNumber);

//        return student.get().getRewards().containsKey("Hair") ? "Yes" : "No";
        if(student.get().getRewards().containsKey("Hair")) {
            HashMap<String, Integer> NewElements = new HashMap<>();
            NewElements.put("Hair", 20);
            NewElements.put("Eye", 30);

            HashMap<String, Integer> OldElements = student.get().getRewards();

            for(String key: NewElements.keySet()) {
                System.out.println(key);
                if(OldElements.containsKey(key)) {
                    // old elements -> Hair : 20
                    // 1000 + 20 -> 1020
                    student.get().setScore(student.get().getScore() + OldElements.get(key));
                    //score 1000
                    // new element -> Hair : 30
                    // 1020 - 30 -> 990
                    student.get().setScore(student.get().getScore() - NewElements.get(key));
                    OldElements.put(key, NewElements.get(key));
                }

                student.get().setRewards(OldElements);
                studentRepository.save(student.get());
            }
        }

    }

    public void RemoveElement(String elementName) {
        Optional<Student> student = studentRepository.findById("645182");

        HashMap<String, Integer> OldElements = student.get().getRewards();
        int difference = student.get().getScore() - OldElements.get(elementName);
        if(difference > 0) {
            student.get().setScore(difference);
        }
        OldElements.remove(elementName);
        student.get().setRewards(OldElements);
        studentRepository.save(student.get());

        }
    }

