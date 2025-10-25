package com.example.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.employee.exception.EmployeeNotFoundException;
import com.example.employee.kafka.EmployeeEvent;
import com.example.employee.kafka.EmployeeProducer;
import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
    private EmployeeProducer producer;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.repository = employeeRepository;
	}

	public Page<Employee> getAllEmployees(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Employee getEmployeeById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
	}

	public Employee createEmployee(Employee employee) {
		Employee savedEmployee = repository.save(employee);
        producer.sendMessage(new EmployeeEvent("EMPLOYEE_CREATED", savedEmployee));
		return repository.save(employee);
	}

	public Employee updateEmployee(Long id, Employee updatedEmployee) {
		Employee employee = repository.findById(id).orElse(null);
		if (employee != null) {
			employee.setName(updatedEmployee.getName());
			employee.setEmail(updatedEmployee.getEmail());
			employee.setContact(updatedEmployee.getContact());
			employee.setDepartment(updatedEmployee.getDepartment());
			employee.setSalary(updatedEmployee.getSalary());
			employee.setHireDate(updatedEmployee.getHireDate());
			
			Employee saved = repository.save(employee);
	        producer.sendMessage(new EmployeeEvent("EMPLOYEE_UPDATED", saved));
			return repository.save(employee);
		}
		return null;
	}

	public void deleteEmployee(Long id) {
		Employee emp = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
		repository.deleteById(id);
		producer.sendMessage(new EmployeeEvent("EMPLOYEE_DELETED", emp));
	}
}
