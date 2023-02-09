package com.edu.miu.TeacherAdminstration;

import com.edu.miu.TeacherAdminstration.domain.*;
import com.edu.miu.TeacherAdminstration.domain.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TeacherAdminstrationApplication implements CommandLineRunner {
	@Autowired
	RestTemplate restTemplate;

	public static void main(String[] args) {

		SpringApplication.run(TeacherAdminstrationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// School object 1
		Teacher teacher1 = new Teacher("T001", "John", "Doe",
				new Contact("johndoe@gmail.com", "555-567-1234"),
				new School("S001", "Brighton High School",
						new Address("123 Main St", "Brighton", "CO", "80603"),
						new Contact("brightonhs@email.com", "303-555-1212")),
				new Class(2022, "A"));

		Teacher teacher2 = new Teacher("T002", "Jane", "Smith",
				new Contact("janesmith@gmail.com", "555-789-5678"),
				new School("S002", "Sunset Middle School",
						new Address("456 Elm Ave", "Sunset", "CO", "80601"),
						new Contact("sunsetms@email.com", "303-555-1313")),
				new Class(2021, "B"));


		String serverURL = "http://localhost:8070/teachers";

		 //add teacher1
		restTemplate.postForLocation(serverURL, teacher1);

		// add teacher2
		restTemplate.postForLocation(serverURL, teacher2);

		// get all
		System.out.println("\n--------------- get all teachers-----------------------\n");
		Teachers teachers = restTemplate.getForObject(serverURL, Teachers.class);
		System.out.println(teachers);


		// get teacher1
		School school = restTemplate.getForObject(serverURL+"/{teacherId}", School.class,"T001");
		System.out.println("\n---------------- get Teacher1 -----------------------\n");
		System.out.println(school);


		// delete teacher1
		restTemplate.delete(serverURL+"/{teacherId}", "T001");

		// update teacher2
		teacher2.setFirstName("Alexis");
		restTemplate.put(serverURL+"/{teacherId}", teacher2, teacher2.getTeacherId());

	}

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
}
