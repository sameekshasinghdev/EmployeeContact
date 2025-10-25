package com.example.employee.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeProducer {

    private static final String TOPIC = "employee-events";

    @Autowired
    private KafkaTemplate<String, EmployeeEvent> kafkaTemplate;

    public void sendMessage(EmployeeEvent event) {
        kafkaTemplate.send(TOPIC, event);
        System.out.println("✅ Event sent to Kafka: " + event);
    }
}
