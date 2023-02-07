package com.edu.miu.TeacherService.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailPublisher {
    private final KafkaTemplate<String, EmailMessage> kafkaTemplate;

    public void publish(String topic, EmailMessage emailMessage){
        System.out.println("sending message "+ emailMessage);
        kafkaTemplate.send(topic, emailMessage);
    }
}