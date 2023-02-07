package com.edu.miu.TeacherService.domain;

import lombok.Data;

@Data
public class Address {
    private String street;
    private String city;
    private String state;
    private String zip;
}