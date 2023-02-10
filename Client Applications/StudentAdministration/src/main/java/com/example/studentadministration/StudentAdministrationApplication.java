package com.example.studentadministration;

import com.example.studentadministration.Domain.Student;
import com.example.studentadministration.Domain.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StudentAdministrationApplication implements CommandLineRunner {

    @Autowired
    RestTemplate restTemplate;


    public static void main(String[] args) {
        SpringApplication.run(StudentAdministrationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String serviceUrl = "http://localhost:8090";

//		System.out.println("\n--------------- get all Students-----------------------\n");
//		Students students = restTemplate.getForObject(serviceUrl +"/students", Students.class);
//		System.out.println(students);

//        System.out.println("\n--------------- get one Students-----------------------\n");
//        Student student = restTemplate.getForObject(serviceUrl+"/student/{studentId}", Student.class, "645185");
//        System.out.println(student);

//        System.out.println("\n--------------- delete one Students-----------------------\n");
//        restTemplate.delete(serviceUrl+"/student/{studentId}", "6451868");

//        System.out.println("\n--------------- add one Students-----------------------\n");
//        Student student = new Student("Doe", "John", "132", "Harvard", "2023", 1000);
//        restTemplate.postForLocation(serviceUrl, student);

//        System.out.println("\n--------------- update one Students-----------------------\n");
//        Student student = new Student("Kibebe", "John", "132", "Harvard", "2023", 1000);
//        restTemplate.put(serviceUrl+"/student/{studentId}/edit", student, "132");

        //Student Login LoadBalancer
        System.out.println("\n--------------- Student Login LoadBalancer-----------------------\n");
        ResponseEntity<String> response = restTemplate.getForEntity(serviceUrl, String.class);
        String rawJson = response.getBody();
        System.out.println(rawJson);


    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
