package com.example.employee.repository;

import org.springframework.stereotype.Repository;

import com.example.employee.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
