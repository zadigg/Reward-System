package com.edu.miu.TeacherService.integration;

import com.edu.miu.TeacherService.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Listener {
    private final EmailSenderService emailSenderService;

    @KafkaListener(topics = "newTopic")
    public void receiveEmailMessage(@Payload EmailMessage emailMessage){

        emailSenderService.sendMail(emailMessage);
    }
}
