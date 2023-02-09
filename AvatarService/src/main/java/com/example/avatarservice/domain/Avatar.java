package com.example.avatarservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Avatar")
public class Avatar {
    @Id
    private Long id;
    private int head = 0;
    private int hair= 0;
    private int eye = 0;
    private int eyebrow = 0;
    private int nose = 0;
    private int mouth = 0;
    private int ears = 0;
    private int body = 0;
    private int hat = 0;
    private int top = 0;
    private int topColor = 0;
    private int hatColor = 0;

    Student student;

//    private Student student;

}
