package com.example.studentservice.Service;

import com.example.studentservice.Controller.StudentController;
import com.example.studentservice.Domain.Avatar;
import com.example.studentservice.Domain.Element;
import com.example.studentservice.Domain.Student;
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
        ResponseEntity<List<Element>> response = restTemplate.exchange("http://localhost:8084/elements", HttpMethod.GET, entity, new ParameterizedTypeReference<List<Element>>() {
        });
        System.out.println(response.getBody().toString());

        Boolean checkIfElementIsThere = false;
        int elementPrice = 0;
        for (Element e : response.getBody()) {
            if (e.getType().equalsIgnoreCase(element)) {
                checkIfElementIsThere = true;
                elementPrice = e.getPrice();
                System.out.println("The element price is  " + e.getPrice());
                break;
            }
        }


        if (student.isPresent() && checkIfElementIsThere) {
            ResponseEntity<Avatar> avatarResponse = restTemplate.exchange("http://localhost:8085/avatars/" + studentNumber, HttpMethod.GET, entity, new ParameterizedTypeReference<Avatar>() {
            });
            System.out.println(avatarResponse.getBody().toString());

            if (element.equalsIgnoreCase("hair")) {
                System.out.println("Price of hair is " + avatarResponse.getBody().getHair());
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getBody().getHair());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.getBody().setHair(0);
                System.out.println("Price of hair after setting it to 0 " + avatarResponse.getBody().getHair());
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.getBody().setHair(elementPrice);
                    System.out.println("Price of hair after setting it to the new elment " + elementPrice + " " + avatarResponse.getBody().getHair());
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/" + element, avatarResponse.getBody(), Boolean.class);
                    studentRepository.save(student.get());

                    return 0;
                } else {
                    System.out.println("Not enough money");
                    return 3;
                }
            } else if (element.equalsIgnoreCase("head")) {
                System.out.println("Price of head is " + avatarResponse.getBody().getHead());
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getBody().getHead());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.getBody().setHead(0);
                System.out.println("Price of head after setting it to 0 " + avatarResponse.getBody().getHead());
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.getBody().setHead(elementPrice);
                    System.out.println("Price of head after setting it to the new element " + elementPrice + " " + avatarResponse.getBody().getHead());
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/" + element, avatarResponse.getBody(), Boolean.class);
                    studentRepository.save(student.get());

                    return 0;
                } else {
                    System.out.println("Not enough money");
                    return 3;
                }
            } else if (element.equalsIgnoreCase("eye")) {
                System.out.println("Price of eye is " + avatarResponse.getBody().getEye());
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getBody().getEye());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.getBody().setEye(0);
                System.out.println("Price of eye after setting it to 0 " + avatarResponse.getBody().getEye());
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.getBody().setEye(elementPrice);
                    System.out.println("Price of eye after setting it to the new element " + elementPrice + " " + avatarResponse.getBody().getEye());
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/" + element, avatarResponse.getBody(), Boolean.class);
                    studentRepository.save(student.get());

                    return 0;
                } else {
                    System.out.println("Not enough money");
                    return 3;
                }
            } else if (element.equalsIgnoreCase("eyebrow")) {
                System.out.println("Price of eyebrow is " + avatarResponse.getBody().getEyebrow());
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getBody().getEyebrow());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.getBody().setEyebrow(0);
                System.out.println("Price of eyebrow after setting it to 0 " + avatarResponse.getBody().getEyebrow());
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.getBody().setEyebrow(elementPrice);
                    System.out.println("Price of eyebrow after setting it to the new element " + elementPrice + " " + avatarResponse.getBody().getEyebrow());
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/" + element, avatarResponse.getBody(), Boolean.class);
                    studentRepository.save(student.get());

                    return 0;
                } else {
                    System.out.println("Not enough money");
                    return 3;
                }
            } else if (element.equalsIgnoreCase("nose")) {
                System.out.println("Price of nose is " + avatarResponse.getBody().getNose());
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getBody().getNose());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.getBody().setNose(0);
                System.out.println("Price of nose after setting it to 0 " + avatarResponse.getBody().getNose());
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.getBody().setNose(elementPrice);
                    System.out.println("Price of nose after setting it to the new element " + elementPrice + " " + avatarResponse.getBody().getNose());
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/" + element, avatarResponse.getBody(), Boolean.class);
                    studentRepository.save(student.get());

                    return 0;
                } else {
                    System.out.println("Not enough money");
                    return 3;
                }
            } else if (element.equalsIgnoreCase("mouth")) {
                System.out.println("Price of mouth is " + avatarResponse.getBody().getMouth());
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getBody().getMouth());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.getBody().setMouth(0);
                System.out.println("Price of mouth after setting it to 0 " + avatarResponse.getBody().getMouth());
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.getBody().setMouth(elementPrice);
                    System.out.println("Price of mouth after setting it to the new element " + elementPrice + " " + avatarResponse.getBody().getMouth());
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/" + element, avatarResponse.getBody(), Boolean.class);
                    studentRepository.save(student.get());

                    return 0;
                } else {
                    System.out.println("Not enough money");
                    return 3;
                }
            } else if (element.equalsIgnoreCase("ears")) {
                System.out.println("Price of ears is " + avatarResponse.getBody().getEars());
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getBody().getEars());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.getBody().setEars(0);
                System.out.println("Price of ears after setting it to 0 " + avatarResponse.getBody().getEars());
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.getBody().setEars(elementPrice);
                    System.out.println("Price of ears after setting it to the new element " + elementPrice + " " + avatarResponse.getBody().getEars());
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/" + element, avatarResponse.getBody(), Boolean.class);
                    studentRepository.save(student.get());

                    return 0;
                } else {
                    System.out.println("Not enough money");
                    return 3;
                }
            } else if (element.equalsIgnoreCase("body")) {
                System.out.println("Price of body is " + avatarResponse.getBody().getBody());
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getBody().getBody());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.getBody().setBody(0);
                System.out.println("Price of body after setting it to 0 " + avatarResponse.getBody().getBody());
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.getBody().setBody(elementPrice);
                    System.out.println("Price of body after setting it to the new element " + elementPrice + " " + avatarResponse.getBody().getBody());
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/" + element, avatarResponse.getBody(), Boolean.class);
                    studentRepository.save(student.get());

                    return 0;
                } else {
                    System.out.println("Not enough money");
                    return 3;
                }
            } else if (element.equalsIgnoreCase("hat")) {
                System.out.println("Price of hat is " + avatarResponse.getBody().getHat());
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getBody().getHat());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.getBody().setHat(0);
                System.out.println("Price of hat after setting it to 0 " + avatarResponse.getBody().getHat());
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.getBody().setHat(elementPrice);
                    System.out.println("Price of hat after setting it to the new element " + elementPrice + " " + avatarResponse.getBody().getHat());
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/" + element, avatarResponse.getBody(), Boolean.class);
                    studentRepository.save(student.get());

                    return 0;
                } else {
                    System.out.println("Not enough money");
                    return 3;
                }
            } else if (element.equalsIgnoreCase("top")) {
                System.out.println("Price of top is " + avatarResponse.getBody().getTop());
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getBody().getTop());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.getBody().setTop(0);
                System.out.println("Price of top after setting it to 0 " + avatarResponse.getBody().getTop());
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.getBody().setTop(elementPrice);
                    System.out.println("Price of top after setting it to the new element " + elementPrice + " " + avatarResponse.getBody().getTop());
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/" + element, avatarResponse.getBody(), Boolean.class);
                    studentRepository.save(student.get());
                    return 0;
                } else {
                    System.out.println("Not enough money");
                    return 3;
                }
            } else if (element.equalsIgnoreCase("topColor")) {
                System.out.println("Price of topColor is " + avatarResponse.getBody().getTopColor());
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getBody().getTopColor());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.getBody().setTopColor(0);
                System.out.println("Price of topColor after setting it to 0 " + avatarResponse.getBody().getTopColor());
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.getBody().setTopColor(elementPrice);
                    System.out.println("Price of topColor after setting it to the new element " + elementPrice + " " + avatarResponse.getBody().getTopColor());
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/" + element, avatarResponse.getBody(), Boolean.class);
                    studentRepository.save(student.get());

                    return 0;
                } else {
                    System.out.println("Not enough money");
                    return 3;
                }
            } else if (element.equalsIgnoreCase("hatColor")) {
                System.out.println("Price of hatColor is " + avatarResponse.getBody().getHatColor());
                System.out.println("Score of student is " + student.get().getScore());
                student.get().setScore(student.get().getScore() + avatarResponse.getBody().getHatColor());
                System.out.println("Score of student after returning the money back to the score " + student.get().getScore());
                avatarResponse.getBody().setHatColor(0);
                System.out.println("Price of hatColor after setting it to 0 " + avatarResponse.getBody().getHatColor());
                if (student.get().getScore() - elementPrice >= 0) {
                    avatarResponse.getBody().setHatColor(elementPrice);
                    System.out.println("Price of hatColor after setting it to the new element " + elementPrice + " " + avatarResponse.getBody().getHatColor());
                    student.get().setScore(student.get().getScore() - elementPrice);
                    System.out.println("Score of student after buying the new element " + student.get().getScore());
                    restTemplate.put("http://localhost:8085/avatars/add-element/645185/" + element, avatarResponse.getBody(), Boolean.class);
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
        restTemplate.delete("http://localhost:8085/avatars/remove/645185/hair", Boolean.class);
        return true;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
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

