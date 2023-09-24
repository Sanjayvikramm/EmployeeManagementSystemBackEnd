package com.employeemanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagementsystem.dto.EmployeeRequest;
import com.employeemanagementsystem.dto.EmployeeResponse;
import com.employeemanagementsystem.entity.Employee;
import com.employeemanagementsystem.service.EmployeeService;
import com.employeemanagementsystem.util.ResponseStructure;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/ems")
public class EmployeeController {
	
	@Autowired
	EmployeeService service;
	
	@PostMapping("/create")
	@CrossOrigin
	public ResponseEntity<ResponseStructure<EmployeeResponse>> create(@RequestBody EmployeeRequest request){
		
		return service.create(request);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<String>> login(@RequestParam String email, @RequestParam String password){
		return service.login(email, password);
	}
	
	@PutMapping("/updateName")
	public ResponseEntity<ResponseStructure<EmployeeResponse>> update(int id,String name){
		return service.update(id, name);
	}
	
	@PutMapping("/updateemail")
	public ResponseEntity<ResponseStructure<EmployeeResponse>> update(String email, int id){
		return service.update(email, id);
	}
	
	@GetMapping("/findById")
	public ResponseEntity<ResponseStructure<EmployeeResponse>> findById(@RequestParam int id){
		return service.findById(id);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<ResponseStructure<List<EmployeeResponse>>> findAll(){
		return service.findAll();
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<ResponseStructure<String>> delete(@RequestParam int id){
		return  service.delete(id);
	}
	
}
