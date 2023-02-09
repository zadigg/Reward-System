package com.RewardClientApplication.RewardClientApplication;
import com.RewardClientApplication.RewardClientApplication.domain.Reward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RewardClientApplication implements CommandLineRunner {
	@Autowired
	RestTemplate restTemplate;
	public static void main(String[] args) {
		SpringApplication.run(RewardClientApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Reward reward1= new Reward("1", "Reward1", 8, "IN_SCHOOL",563.0);
		Reward reward2= new Reward("2", "Reward2", 9, "IN_SCHOOL",563.0);

		String serverURL = "http://localhost:8090/rewards";

		// add reward
		restTemplate.postForLocation(serverURL, reward1);
		restTemplate.postForLocation(serverURL, reward2);


		// get all rewards
		System.out.println("\n--------------- get all rewards-----------------------\n");
		Reward rewards = restTemplate.getForObject(serverURL, Reward.class);
		System.out.println(rewards);


//		// get reward1
//		Reward reward = restTemplate.getForObject(serverURL+"/{rewardId}", Reward.class,"01");
//		System.out.println("\n---------------- get reward-----------------------\n");
//		System.out.println(reward);

//
//		// delete reward1
//		restTemplate.delete(serverURL+"/{rewardId}", "01");
//
//		// update reward2
//		reward2.setName("Reward2");
//		restTemplate.put(serverURL+"/{rewardId}", reward2, reward2.getRewardId());


	}

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
}

