package com.employeemanagementsystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employeemanagementsystem.customexception.EmailNotFoundException;
import com.employeemanagementsystem.dto.EmployeeRequest;
import com.employeemanagementsystem.dto.EmployeeResponse;
import com.employeemanagementsystem.entity.Employee;
import com.employeemanagementsystem.repository.EmployeeRepository;
import com.employeemanagementsystem.util.ResponseStructure;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository repository;
	
	
	public ResponseEntity<ResponseStructure<EmployeeResponse>> create(EmployeeRequest request){
		Employee employee = new Employee();
		employee.setEmail(request.getEmail());
		employee.setName(request.getName());
		employee.setPassword(request.getPassword());
		
		employee = repository.save(employee);
		
		EmployeeResponse response = new EmployeeResponse();
		response.setId(employee.getId());
		response.setName(employee.getName());
		response.setEmail(employee.getEmail());
		
		ResponseStructure<EmployeeResponse> responseStructure = new ResponseStructure<EmployeeResponse>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("EmployeeCreated");
		responseStructure.setData(response);
		
		return new ResponseEntity<ResponseStructure<EmployeeResponse>>(responseStructure,HttpStatus.CREATED);

	}
	
	public ResponseEntity<ResponseStructure<String>> login(String email,String password){
		System.out.println(email);
		Optional<Employee> OptionalEmployee = repository.findByEmail(email);
		if(OptionalEmployee.isPresent()) {
			Employee employee = OptionalEmployee.get();
			System.out.println(employee.getEmail());
			if (employee.getPassword().equals(password)) {
				ResponseStructure<String> responseStructure = new ResponseStructure<String>();
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setMessage("process success");
				responseStructure.setData("Login successful");
				return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK);
			}
			else {
				ResponseStructure<String> responseStructure = new ResponseStructure<String>();
				responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
				responseStructure.setMessage("password problem");
				responseStructure.setData("Incorrect Password");
				return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.BAD_REQUEST);
			}
		}
		throw new EmailNotFoundException("Employee Email Not Found");
	}
}
