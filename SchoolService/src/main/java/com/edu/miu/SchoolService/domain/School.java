package com.edu.miu.SchoolService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class School {
    @Id
    private String schoolId;
    private String name;
    private Address address;
    private Contact contact;
}
