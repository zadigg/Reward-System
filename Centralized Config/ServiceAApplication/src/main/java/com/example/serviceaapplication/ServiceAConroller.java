package com.example.serviceaapplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ServiceAConroller {

    @Value("${name}")
    private String greeting;

    @RequestMapping("/")
    public String getName() { return greeting;}
}
