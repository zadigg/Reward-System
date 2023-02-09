package com.edu.miu.SchoolAdminstration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class School {
    private String schoolId;
    private String name;
    private Address address;
    private Contact contact;
}
