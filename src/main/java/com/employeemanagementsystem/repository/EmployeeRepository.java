package com.employeemanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagementsystem.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
	
	public Optional<Employee> findByEmail(String email);

}
