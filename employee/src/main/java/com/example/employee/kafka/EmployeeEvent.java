package com.example.employee.kafka;

import com.example.employee.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEvent {
    private String eventType;   // e.g., EMPLOYEE_CREATED, EMPLOYEE_UPDATED
    private Employee employee;
}
