package com.edu.miu.TeacherService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Teacher {
    @Id
    private String teacherId;
    private String firstName;
    private String lastName;
    private Contact contact;
    private School school;
    private Class teachingClass;
}