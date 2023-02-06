package com.example.avatarservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Avatar {
    @Id
    private Long id;
    private String head;
    private String hair;
    private String eye;
    private String eyebrow;
    private String nose;
    private String mouth;
    private String ears;
    private String body;
    private String hat;
    private String top;
    private String topColor;
    private String hatColor;

}
