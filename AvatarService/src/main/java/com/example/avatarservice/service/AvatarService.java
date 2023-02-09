package com.example.avatarservice.service;

import com.example.avatarservice.domain.Avatar;
import com.example.avatarservice.domain.Element;
import com.example.avatarservice.domain.Student;
import com.example.avatarservice.repository.AvatarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AvatarService implements IAvatarService {
    @Autowired
    private AvatarRepo avatarRepo;

    @Autowired
    RestTemplate restTemplate;
    @Override
    public Boolean addAvatar(Avatar avatar, Long studentNumber) {
        Optional<Student> student = Optional.ofNullable(restTemplate.getForObject("http://localhost:8090/student/"+studentNumber, Student.class));
        System.out.println(student);

        if (student.isPresent()){
            avatar.setStudent(student.get());
            avatar.setId(studentNumber);
            avatarRepo.save(avatar);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Boolean addAvatarToStudent(Long studentNumber, String elementType){
        // 645185 // Hair
        Optional<Student> student = Optional.ofNullable(restTemplate.getForObject("http://localhost:8090/student/"+ studentNumber, Student.class));
        System.out.println(student);
        Optional<Avatar> avatar= avatarRepo.findById(studentNumber);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<List<Element>> response = restTemplate.exchange("http://localhost:8084/elements", HttpMethod.GET,entity, new ParameterizedTypeReference<List<Element>>() {});
        System.out.println(response.getBody().toString());
        int priceOfElement = 0;
        for(Element element: response.getBody()){
            if(element.getType().equals(elementType)){
                    priceOfElement = element.getPrice();
            }
        }

        System.out.println(priceOfElement);

        if (student.isPresent() && avatar.isPresent()){
            if(elementType.equalsIgnoreCase("Hair"))
                avatar.get().setHair(priceOfElement);
            else if(elementType.equalsIgnoreCase("Eye"))
                avatar.get().setEye(priceOfElement);
            else if(elementType.equalsIgnoreCase("head"))
                avatar.get().setHead(priceOfElement);
            else if(elementType.equalsIgnoreCase("eyebrow"))
                avatar.get().setEyebrow(priceOfElement);
            else if(elementType.equalsIgnoreCase("nose"))
                avatar.get().setNose(priceOfElement);
            else if(elementType.equalsIgnoreCase("mouth"))
                avatar.get().setMouth(priceOfElement);
            else if(elementType.equalsIgnoreCase("ears"))
                avatar.get().setEars(priceOfElement);
            else if(elementType.equalsIgnoreCase("body"))
                avatar.get().setBody(priceOfElement);
            else if(elementType.equalsIgnoreCase("hat"))
                avatar.get().setHat(priceOfElement);
            else if(elementType.equalsIgnoreCase("top"))
                avatar.get().setTop(priceOfElement);
            else if(elementType.equalsIgnoreCase("topColor"))
                avatar.get().setTopColor(priceOfElement);
            else if(elementType.equalsIgnoreCase("hatColor"))
                avatar.get().setHatColor(priceOfElement);
            else
                return false;
            avatarRepo.save(avatar.get());
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public Avatar updateAvatar(Long avatarId ,Avatar avatar) {
        Optional<Avatar> oldAvatar= avatarRepo.findById(avatarId);
        if (oldAvatar.isPresent()){
            Avatar updatedAvatar = avatarRepo.save(avatar);
            return updatedAvatar;
        }

        else {
            return avatarRepo.save(avatar);
        }

    }

    @Override
    public boolean deleteAvatar(Long studentNumber, @PathVariable String elementType) {
        Avatar avatar = getAvatarById(studentNumber);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<List<Student>> response = restTemplate.exchange("http://localhost:8090/students", HttpMethod.GET, entity, new ParameterizedTypeReference<List<Student>>() {
        });
        System.out.println(response.getBody().toString());

//        Student student = restTemplate.getForObject("http://localhost:8090/student/"+studentNumber, Student.class);
        if(elementType.equalsIgnoreCase("Hair")) {
            restTemplate.postForObject("http://localhost:8080/student/" +studentNumber + "/changeScore/" + avatar.getHair(), avatar.getHair(), String.class);
            avatar.setHair(0);
        }
        else if(elementType.equalsIgnoreCase("Eye")) {
            restTemplate.postForObject("http://localhost:8080/student/" +studentNumber + "/changeScore/" + avatar.getEye(), avatar.getEye(), String.class);
            avatar.setEye(0);
        }
        else if(elementType.equalsIgnoreCase("head")) {
            restTemplate.postForObject("http://localhost:8080/student/" +studentNumber + "/changeScore/" + avatar.getHead(), avatar.getHead(), String.class);
            avatar.setHead(0);
        }
        else if(elementType.equalsIgnoreCase("eyebrow")) {
            restTemplate.postForObject("http://localhost:8080/student/" +studentNumber + "/changeScore/" + avatar.getEyebrow(), avatar.getEyebrow(), String.class);
            avatar.setEyebrow(0);
        }
        else if(elementType.equalsIgnoreCase("nose")) {
            restTemplate.postForObject("http://localhost:8080/student/" +studentNumber + "/changeScore/" + avatar.getNose(), avatar.getNose(), String.class);
            avatar.setNose(0);
        }
        else if(elementType.equalsIgnoreCase("mouth")) {
            restTemplate.postForObject("http://localhost:8080/student/" +studentNumber + "/changeScore/" + avatar.getMouth(), avatar.getMouth(), String.class);
            avatar.setMouth(0);
        }
        else if(elementType.equalsIgnoreCase("ears")) {
            restTemplate.postForObject("http://localhost:8080/student/" +studentNumber + "/changeScore/" + avatar.getEars(), avatar.getEars(), String.class);
            avatar.setEars(0);
        }
        else if(elementType.equalsIgnoreCase("body")) {
            restTemplate.postForObject("http://localhost:8080/student/" +studentNumber + "/changeScore/" + avatar.getBody(), avatar.getBody(), String.class);
            avatar.setBody(0);
        }
        else if(elementType.equalsIgnoreCase("hat")) {
            restTemplate.postForObject("http://localhost:8080/student/" +studentNumber + "/changeScore/" + avatar.getHat(), avatar.getHat(), String.class);
            avatar.setHat(0);
        }
        else if(elementType.equalsIgnoreCase("top")) {
            restTemplate.postForObject("http://localhost:8080/student/" +studentNumber + "/changeScore/" + avatar.getTop(), avatar.getTop(), String.class);
            avatar.setTop(0);
        }
        else if(elementType.equalsIgnoreCase("topColor")) {
            restTemplate.postForObject("http://localhost:8080/student/" +studentNumber + "/changeScore/" + avatar.getTopColor(), avatar.getTopColor(), String.class);

            avatar.setTopColor(0);
        }
        else if(elementType.equalsIgnoreCase("hatColor")) {
            restTemplate.postForObject("http://localhost:8080/student/" +studentNumber + "/changeScore/" + avatar.getHatColor(), avatar.getHatColor(), String.class);
            avatar.setHatColor(0);
        }
        avatarRepo.save(avatar);

        return true;
    }

    @Override
    public Avatar getAvatarById(Long avatarId) {
        Optional<Avatar> avatar= avatarRepo.findById(avatarId);
       return avatar.orElse(null);
    }

    @Override
    public List<Avatar> getAllAvatars() {
        return avatarRepo.findAll();
    }
}
