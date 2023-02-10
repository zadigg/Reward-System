package com.example.configserverlocal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerLocalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerLocalApplication.class, args);
    }

}
