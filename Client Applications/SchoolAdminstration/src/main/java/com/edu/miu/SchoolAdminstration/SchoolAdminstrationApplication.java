package com.edu.miu.SchoolAdminstration;

import com.edu.miu.SchoolAdminstration.domain.Address;
import com.edu.miu.SchoolAdminstration.domain.Contact;
import com.edu.miu.SchoolAdminstration.domain.School;
import com.edu.miu.SchoolAdminstration.domain.Schools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SchoolAdminstrationApplication implements CommandLineRunner {
	@Autowired
	RestTemplate restTemplate;


	public static void main(String[] args) {

		SpringApplication.run(SchoolAdminstrationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// School object 1
		School school1 = new School("S001", "Brighton High School",
				new Address("123 Main St", "Brighton", "CO", "80603"),
				new Contact("brightonhs@email.com", "303-555-1212"));

		// School object 2
		School school2 = new School("S002", "Sunset Middle School",
				new Address("456 Elm Ave", "Sunset", "CO", "80601"),
				new Contact("sunsetms@email.com", "303-555-1313"));

		String serverURL = "http://localhost:8070/schools";

		// add school1
		restTemplate.postForLocation(serverURL, school1);

		// add school2
		restTemplate.postForLocation(serverURL, school2);
//
//		// get all
//		System.out.println("\n--------------- get all schools-----------------------\n");
//		Schools schools = restTemplate.getForObject(serverURL, Schools.class);
//		System.out.println(schools);
//
//
//		// get school1
//		School school = restTemplate.getForObject(serverURL+"/{schoolId}", School.class,"S001");
//		System.out.println("\n---------------- get School1-----------------------\n");
//		System.out.println(school);
//
//
		// delete School1
		restTemplate.delete(serverURL+"/{schoolId}", "S001");
//
//		// update school2
//		school2.setName("Lincoln High School");
//		restTemplate.put(serverURL+"/{schoolId}", school2, school2.getSchoolId());
//

	}

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
}
