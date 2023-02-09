package UserClientApplication;

import UserClientApplication.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserClientApplication implements CommandLineRunner {
	@Autowired
	RestTemplate restTemplate;
	public static void main(String[] args) {
		SpringApplication.run(UserClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user1= new User("admin1", "password1", "ADMIN");
		User user2= new User("admin2", "password1", "TEACHER");

		String serverURL = "http://localhost:8090/users";

		// add user
		restTemplate.postForLocation(serverURL, user1);
		restTemplate.postForLocation(serverURL, user2);


		// get all users
		System.out.println("\n--------------- get all users-----------------------\n");
		User users = restTemplate.getForObject(serverURL, User.class);
		System.out.println(users);


		// get user1
		User user = restTemplate.getForObject(serverURL+"/{userName}", User.class,"01");
		System.out.println("\n---------------- get user-----------------------\n");
		System.out.println(user);


//		// delete user1
//		restTemplate.delete(serverURL+"/{userName}", "01");
//
//		// update user2
//		user2.setName("User2");
//		restTemplate.put(serverURL+"/{userName}", user2, user2.getName());


	}

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
}

