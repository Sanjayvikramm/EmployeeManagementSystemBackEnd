package com.employeemanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagementsystem.dto.EmployeeRequest;
import com.employeemanagementsystem.dto.EmployeeResponse;
import com.employeemanagementsystem.service.EmployeeService;
import com.employeemanagementsystem.util.ResponseStructure;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/ems")
public class EmployeeController {
	
	@Autowired
	EmployeeService service;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseStructure<EmployeeResponse>> create(@RequestBody EmployeeRequest request){
		
		return service.create(request);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<String>> login(@RequestParam String email, @RequestParam String password){
		return service.login(email, password);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<EmployeeResponse>> update(){
		return null;
		
	}
}
