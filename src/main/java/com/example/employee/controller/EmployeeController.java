package com.example.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	 private final EmployeeService employeeService;
	 
	 @Autowired
	    public EmployeeController(EmployeeService employeeService) {
	        this.employeeService = employeeService;
	    }
	 
	 
	   @GetMapping
	    public List<Employee> getAllEmployees() {
	        return employeeService.getAllEmployees();
	    }
	   
	   @GetMapping("/{id}")
	    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
	        return employeeService.getEmployeeById(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	    }
	   
		/*
		 * @PostMapping public Employee createEmployee(@Valid @RequestBody Employee
		 * employee) { return employeeService.saveEmployee(employee); }
		 */
	   
	   @PostMapping
	    public ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee) {
		   try {
	            Employee savedEmployee = employeeService.saveEmployee(employee);
	            return ResponseEntity.ok(savedEmployee);
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }
	   
	   
	   @PutMapping("/{id}")
	    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
	        return employeeService.getEmployeeById(id)
	                .map(employee -> {
	                	employee.setName(updatedEmployee.getName());
	                	employee.setRole(updatedEmployee.getRole());
	                	Employee savedEmployee = employeeService.saveEmployee(employee);
	                    return ResponseEntity.ok(savedEmployee);
	                })
	                .orElse(ResponseEntity.notFound().build());
	    }
	   
	   @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
	        if (employeeService.getEmployeeById(id).isPresent()) {
	        	employeeService.deleteEmployee(id);
	            return ResponseEntity.noContent().build();
	        }
	        return ResponseEntity.notFound().build();
	    }
}
