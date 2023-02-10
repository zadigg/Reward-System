package com.example.avataradminstration;

import com.example.avataradminstration.domain.Avatar;
import com.example.avataradminstration.domain.Avatars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AvatarAdminstrationApplication implements CommandLineRunner {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(AvatarAdminstrationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Element element = new Element("hair", 20);
//
        String serverURL = "http://localhost:8085/avatars";

        Avatar avatar = new Avatar(20, 30, 40,80, 10, 20, 30, 40, 50, 20, 0,10);
//
//        System.out.println("\n--------------- add Avatar-----------------------\n");
//        //student is required to add avatar
//        restTemplate.postForLocation("http://localhost:8085/avatars/add-avatars/645185", avatar);

//        System.out.println("\n--------------- get all Avatar-----------------------\n");
//        Avatars avatars = restTemplate.getForObject("http://localhost:8085/avatars", Avatars.class);
//        System.out.println(avatars);

//        System.out.println("\n--------------- get single Avatar-----------------------\n");
//        Avatar element1 = restTemplate.getForObject(serverURL+"/645185", Avatar.class);
//        System.out.println(element1);

//        System.out.println("\n--------------- update Element-----------------------\n");
//        Avatar avatarForUpdate = new Avatar(50, 30, 40,80, 10, 20, 30, 40, 50, 20, 0,10);
//        restTemplate.postForLocation("http://localhost:8085/avatars/add-avatars/645185", avatarForUpdate);

//        System.out.println("\n--------------- delete Element-----------------------\n");
//        restTemplate.delete("http://localhost:8085/avatars/remove/645185/head");
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
