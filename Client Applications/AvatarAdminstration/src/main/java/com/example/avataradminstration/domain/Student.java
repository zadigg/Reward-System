package com.example.avataradminstration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
    private String firstName;
    private String lastName;

    private String studentNumber;
    private String school;
    private String classOf;
    private int score;


}
