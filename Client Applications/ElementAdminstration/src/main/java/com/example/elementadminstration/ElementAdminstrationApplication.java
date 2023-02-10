package com.example.elementadminstration;

import com.example.elementadminstration.domain.Element;
import com.example.elementadminstration.domain.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ElementAdminstrationApplication implements CommandLineRunner {

    @Autowired
    RestTemplate restTemplate;
    public static void main(String[] args) {
        SpringApplication.run(ElementAdminstrationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Element element = new Element("lol", 20);

        String serverURL = "http://localhost:8084/elements";

//
//        System.out.println("\n--------------- add Element-----------------------\n");
//        restTemplate.postForLocation(serverURL+"/save", element);

//        System.out.println("\n--------------- get all Elements-----------------------\n");
//        Elements elements = restTemplate.getForObject("http://localhost:8084/elements", Elements.class);
//        System.out.println(elements);

//        System.out.println("\n--------------- get single Element-----------------------\n");
//        Element element1 = restTemplate.getForObject(serverURL+"/{elementId}", Element.class,"head");
//        System.out.println(element1);

//        System.out.println("\n--------------- update Element-----------------------\n");
//        Element elementForUpdate = new Element("hair", 40);
//        restTemplate.put(serverURL+"/update/hair", elementForUpdate);

//        System.out.println("\n--------------- delete Element-----------------------\n");
//        restTemplate.delete(serverURL+"/{elementId}", "top");

    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
