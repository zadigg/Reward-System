package com.example.rewardadminstration;

import com.example.rewardadminstration.domain.Avatar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RewardAdminstrationApplication implements CommandLineRunner {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RewardAdminstrationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String serverURL = "http://localhost:8090";

//        System.out.println("\n------------------- Add Element from student avatar start -------------------\n");
//        restTemplate.put("http://localhost:8080/student/645185/mouth", Entity.class);

//        System.out.println("\n------------------- remove Element from student avatar start -------------------\n");
//        restTemplate.delete("http://localhost:8090/student/645185/eye");

        System.out.println("\n------------------- View Elements bought by specific student -------------------\n");
        Avatar element1 = restTemplate.getForObject("http://localhost:8085/avatars/645185", Avatar.class);
        System.out.println(element1);
    }


    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
