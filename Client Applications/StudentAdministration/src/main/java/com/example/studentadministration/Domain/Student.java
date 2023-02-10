package com.example.studentadministration.Domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


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
//    HashMap<String, Integer> rewards;

}
