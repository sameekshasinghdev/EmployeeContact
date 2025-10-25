package com.example.employee.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmployeeConsumer {

    @KafkaListener(topics = "employee-events", groupId = "employee-service-group")
    public void consume(EmployeeEvent event) {
        System.out.println("📩 Received Kafka Event: " + event);
    }
}
