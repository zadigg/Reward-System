package com.edu.miu.TeacherAdminstration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private String teacherId;
    private String firstName;
    private String lastName;
    private Contact contact;
    private School school;
    private Class teachingClass;
}