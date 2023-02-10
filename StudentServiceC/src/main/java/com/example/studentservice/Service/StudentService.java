package com.example.studentservice.Service;

import com.example.studentservice.Controller.StudentController;
import com.example.studentservice.Domain.*;
import com.example.studentservice.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RestTemplate restTemplate;


    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    public Boolean deleteStudent(String studentNumber) {
        Optional<Student> student = studentRepository.findById(studentNumber);
        if (student.isPresent()) {
            studentRepository.delete(student.get());
            return true;
        } else {
            return false;
        }
    }

    public Boolean updateStudent(Student student, String studentNumber) {
        Optional<Student> student1 = studentRepository.findById(studentNumber);
        if (student1.isPresent()) {
            student1.get().setFirstName(student.getFirstName());
            student1.get().setLastName(student.getLastName());
            student1.get().setScore(student.getScore());

            student1.get().setClassOf(student.getClassOf());
            student1.get().setSchool(student.getSchool());
            studentRepository.save(student1.get());
            return true;
        } else {
            return false;
        }
    }


    public int AddElement(String studentNumber, String element) {

        Optional<Student> student = studentRepository.findById(studentNumber);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
       Elements response = restTemplate.getForObject("http://localhost:8084/elements", Elements.class);
        System.out.println(response.toString());

        Boolean checkIfElementIsThere = false;
        int elementPrice = 0;
        for (Element e : response.getElements()) {
            if (e.getType().equalsIgnoreCase(element)) {
                checkIfElementIsThere = true;
                elementPrice = e.getPrice();
                System.out.println("The element price is  " + e.getPrice());
                break;
            }
        }


        if (student.isPresent() && checkIfElementIsThere) {
//            ResponseEntity<Avatar> avatarResponse = restTemplate.exchange("http://localhost:8085/avatars/" + studentNumber, HttpMethod.GET, entity, new ParameterizedTypeReference<Avatar>() {
//            });
            Avatar avatarResponse = restTemplate.getForObject("http://localhost:8085/avatars/"+ studentNumber, Avatar.class);
            System.out.println(avatarResponse.toString());

            if (element.equalsIgnoreCase("hair")) {
                System.out.println("Price of hair is " + avatarResponse.getHair());
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getHair());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.setHair(0);
                System.out.println("Price of hair after setting it to 0 " + avatarResponse.getHair());
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.setHair(elementPrice);
                    System.out.println("Price of hair after setting it to the new elment " + elementPrice + " " + avatarResponse.getHair());
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/hair", Boolean.class);
                    studentRepository.save(student.get());

                    return 0;
                } else {
                    System.out.println("Not enough money");
                    return 3;
                }
            } else if (element.equalsIgnoreCase("head")) {
                System.out.println("Price of head is " + avatarResponse.getHead());
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getHead());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.setHead(0);
                System.out.println("Price of head after setting it to 0 " + avatarResponse.getHead());
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.setHead(elementPrice);
                    System.out.println("Price of head after setting it to the new element " + elementPrice + " " + avatarResponse.getHead());
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/" + element, avatarResponse, Boolean.class);
                    studentRepository.save(student.get());

                    return 0;
                } else {
                    System.out.println("Not enough money");
                    return 3;
                }
            } else if (element.equalsIgnoreCase("eye")) {
                System.out.println("Price of eye is " + avatarResponse.getEye());
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getEye());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.setEye(0);
                System.out.println("Price of eye after setting it to 0 " + avatarResponse.getEye());
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.setEye(elementPrice);
                    System.out.println("Price of eye after setting it to the new element " + elementPrice + " " + avatarResponse.getEye());
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/" + element, avatarResponse, Boolean.class);
                    studentRepository.save(student.get());

                    return 0;
                } else {
                    System.out.println("Not enough money");
                    return 3;
                }
            } else if (element.equalsIgnoreCase("eyebrow")) {
                System.out.println("Price of eyebrow is " + avatarResponse.getEyebrow());
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getEyebrow());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.setEyebrow(0);
                System.out.println("Price of eyebrow after setting it to 0 " + avatarResponse.getEyebrow());
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.setEyebrow(elementPrice);
                    System.out.println("Price of eyebrow after setting it to the new element " + elementPrice + " " + avatarResponse.getEyebrow());
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/" + element, avatarResponse, Boolean.class);
                    studentRepository.save(student.get());

                    return 0;
                } else {
                    System.out.println("Not enough money");
                    return 3;
                }
            } else if (element.equalsIgnoreCase("nose")) {
                System.out.println("Price of nose is " + avatarResponse.getNose());
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getNose());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.setNose(0);
                System.out.println("Price of nose after setting it to 0 " + avatarResponse.getNose());
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.setNose(elementPrice);
                    System.out.println("Price of nose after setting it to the new element " + elementPrice + " " + avatarResponse.getNose());
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/" + element, avatarResponse, Boolean.class);
                    studentRepository.save(student.get());

                    return 0;
                } else {
                    System.out.println("Not enough money");
                    return 3;
                }
            } else if (element.equalsIgnoreCase("mouth")) {
                System.out.println("Price of mouth is " + avatarResponse.getMouth());
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getMouth());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.setMouth(0);
                System.out.println("Price of mouth after setting it to 0 " + avatarResponse.getMouth());
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.setMouth(elementPrice);
                    System.out.println("Price of mouth after setting it to the new element " + elementPrice + " " + avatarResponse.getMouth());
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/" + element, avatarResponse, Boolean.class);
                    studentRepository.save(student.get());

                    return 0;
                } else {
                    System.out.println("Not enough money");
                    return 3;
                }
            } else if (element.equalsIgnoreCase("ears")) {
                System.out.println("Price of ears is " + avatarResponse.getEars());
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getEars());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.setEars(0);
                System.out.println("Price of ears after setting it to 0 " + avatarResponse.getEars());
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.setEars(elementPrice);
                    System.out.println("Price of ears after setting it to the new element " + elementPrice + " " + avatarResponse.getEars());
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/" + element, avatarResponse, Boolean.class);
                    studentRepository.save(student.get());

                    return 0;
                } else {
                    System.out.println("Not enough money");
                    return 3;
                }
            } else if (element.equalsIgnoreCase("body")) {
                System.out.println("Price of body is " + avatarResponse);
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getBody());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.setBody(0);
                System.out.println("Price of body after setting it to 0 " + avatarResponse);
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.setBody(elementPrice);
                    System.out.println("Price of body after setting it to the new element " + elementPrice + " " + avatarResponse);
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/" + element, avatarResponse, Boolean.class);
                    studentRepository.save(student.get());

                    return 0;
                } else {
                    System.out.println("Not enough money");
                    return 3;
                }
            } else if (element.equalsIgnoreCase("hat")) {
                System.out.println("Price of hat is " + avatarResponse.getHat());
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getHat());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.setHat(0);
                System.out.println("Price of hat after setting it to 0 " + avatarResponse.getHat());
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.setHat(elementPrice);
                    System.out.println("Price of hat after setting it to the new element " + elementPrice + " " + avatarResponse.getHat());
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/" + element, avatarResponse, Boolean.class);
                    studentRepository.save(student.get());

                    return 0;
                } else {
                    System.out.println("Not enough money");
                    return 3;
                }
            } else if (element.equalsIgnoreCase("top")) {
                System.out.println("Price of top is " + avatarResponse.getTop());
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getTop());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.setTop(0);
                System.out.println("Price of top after setting it to 0 " + avatarResponse.getTop());
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.setTop(elementPrice);
                    System.out.println("Price of top after setting it to the new element " + elementPrice + " " + avatarResponse.getTop());
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/" + element, avatarResponse, Boolean.class);
                    studentRepository.save(student.get());
                    return 0;
                } else {
                    System.out.println("Not enough money");
                    return 3;
                }
            } else if (element.equalsIgnoreCase("topColor")) {
                System.out.println("Price of topColor is " + avatarResponse.getTopColor());
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getTopColor());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.setTopColor(0);
                System.out.println("Price of topColor after setting it to 0 " + avatarResponse.getTopColor());
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.setTopColor(elementPrice);
                    System.out.println("Price of topColor after setting it to the new element " + elementPrice + " " + avatarResponse.getTopColor());
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/" + element, avatarResponse, Boolean.class);
                    studentRepository.save(student.get());

                    return 0;
                } else {
                    System.out.println("Not enough money");
                    return 3;
                }
            } else if (element.equalsIgnoreCase("hatColor")) {
                System.out.println("Price of hatColor is " + avatarResponse.getHatColor());
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getHatColor());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.setHatColor(0);
                System.out.println("Price of hatColor after setting it to 0 " + avatarResponse.getHatColor());
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.setHatColor(elementPrice);
                    System.out.println("Price of hatColor after setting it to the new element " + elementPrice + " " + avatarResponse.getHatColor());
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/" + element, avatarResponse, Boolean.class);
                    studentRepository.save(student.get());

                    return 0;
                } else {
                    System.out.println("Not enough money");
                    return 3;
                }



            }
        }
        if (student.isEmpty()) {
            System.out.println("Student is not present");
            return 1;
        }

        if (!checkIfElementIsThere) {
            System.out.println("Element is not present");
            return 2;
        }
        return 0;
    }



    public Boolean RemoveElement(String studentNumber, String elementName) {

        Optional<Student> student = studentRepository.findById(studentNumber);
        restTemplate.delete("http://localhost:8085/avatars/remove/645185/"+ elementName, Boolean.class);
        return true;
    }

    public Students getAllStudents() {
        Students students = new Students();
        students.setStudentList(studentRepository.findAll());
        return students;
    }

    public Student getStudent(String studentNumber) {
        Optional<Student> student = studentRepository.findById(studentNumber);
        if(student.isPresent()) {
            return student.get();
        } else {
            return null;
        }
    }

    public int AddScore(String studentNumber, int score) {
        Optional<Student> student = studentRepository.findById(studentNumber);
        System.out.println("Student is present " + student.isPresent());
        if(student.isPresent()) {
            student.get().setScore(student.get().getScore() + score);
            studentRepository.save(student.get());
            return 0;
        } else {
            return 1;
        }
    }


}

