package com.edu.miu.TeacherService.domain;

import lombok.Data;

@Data
public class School {
    private String schoolId;
    private String name;
    private Address address;
    private Contact contact;
}
