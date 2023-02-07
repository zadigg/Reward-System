package com.edu.miu.TeacherService.integration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessage {
    private String recipient;
    private String subject;
    private String messageBody;

}
