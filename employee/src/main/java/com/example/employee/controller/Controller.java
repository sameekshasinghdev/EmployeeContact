package com.example.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class Controller {
	@Autowired
	private EmployeeService service;

	@GetMapping
	public Page<Employee> getAllEmployees(@PageableDefault(page = 0, size = 5, sort = "id") Pageable pageable) {
		return service.getAllEmployees(pageable);
	}

	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable Long id) {
		return service.getEmployeeById(id);
	}

	@PostMapping
	public Employee createEmployee(@RequestBody Employee employee) {
		return service.createEmployee(employee);
	}

	@PutMapping("/{id}")
	public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		return service.updateEmployee(id, employee);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		service.deleteEmployee(id);
	}

}
