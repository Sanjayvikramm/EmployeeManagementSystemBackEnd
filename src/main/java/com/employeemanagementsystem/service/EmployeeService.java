package com.employeemanagementsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employeemanagementsystem.customexception.EmailNotFoundException;
import com.employeemanagementsystem.customexception.EmployeeIdNotFoundException;
import com.employeemanagementsystem.customexception.ExcistingEmailException;
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
		
		List<Employee> employeeList=repository.findAll();
		for(Employee emp:employeeList) {
			if(emp.getEmail().equals(request.getEmail())) {
				throw new ExcistingEmailException("Email Id Is Already In Excistence");
			}
		}
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
	
		Optional<Employee> optionalEmployee = repository.findByEmail(email);
		
		if(optionalEmployee.isPresent()) {
			Employee employee = optionalEmployee.get();
			
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
	
	public ResponseEntity<ResponseStructure<List<EmployeeResponse>>> findAll(){
		
		List<Employee> employee = repository.findAll();
		
		List<EmployeeResponse> employeeResponse = new ArrayList<EmployeeResponse>();
		
		for(Employee emp:employee) {
			
			EmployeeResponse empResponse = new EmployeeResponse();
			empResponse.setId(emp.getId());
			empResponse.setName(emp.getName());
			empResponse.setEmail(emp.getEmail());
			
			employeeResponse.add(empResponse);	
		}
		
		ResponseStructure<List<EmployeeResponse>> responseStructure = new ResponseStructure<List<EmployeeResponse>>();
		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMessage("Data Found");
		responseStructure.setListData(employeeResponse);
		
		return new ResponseEntity<ResponseStructure<List<EmployeeResponse>>>(responseStructure,HttpStatus.FOUND);
	}
	
	public ResponseEntity<ResponseStructure<EmployeeResponse>> findById(int id){
		
		Optional<Employee> optionalEmployee = repository.findById(id);
		
		if(optionalEmployee.isPresent()) {
			Employee employee = optionalEmployee.get();
			
			EmployeeResponse employeeResponse = new EmployeeResponse();
			employeeResponse.setId(employee.getId());
			employeeResponse.setName(employee.getName());
			employeeResponse.setEmail(employee.getEmail());
			
			ResponseStructure<EmployeeResponse> responseStructure = new ResponseStructure<EmployeeResponse>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Employee Found");
			responseStructure.setData(employeeResponse);
			
			return new ResponseEntity<ResponseStructure<EmployeeResponse>>(responseStructure,HttpStatus.FOUND);
		}
		throw new EmployeeIdNotFoundException("Employee Id Not Found");
		}
	
	public ResponseEntity<ResponseStructure<EmployeeResponse>> update(int id, String name){
		
		Optional<Employee> optionalEmployee = repository.findById(id);
		
		if(optionalEmployee.isPresent()) {
			Employee employee = optionalEmployee.get();
			employee.setName(name);
			employee= repository.save(employee);
			
			EmployeeResponse employeeResponse = new EmployeeResponse();
			employeeResponse.setId(employee.getId());
			employeeResponse.setName(employee.getName());
			employeeResponse.setEmail(employee.getEmail());
			
			ResponseStructure<EmployeeResponse> responseStructure = new ResponseStructure<EmployeeResponse>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Data Updated");
			responseStructure.setData(employeeResponse);
			
			return new ResponseEntity<ResponseStructure<EmployeeResponse>>(responseStructure,HttpStatus.OK);
		}
		throw new EmployeeIdNotFoundException("Employee ID Not Found");
		
	}
	
public ResponseEntity<ResponseStructure<EmployeeResponse>> update(String email,int id){
		
		Optional<Employee> optionalEmployee = repository.findById(id);
		
		if(optionalEmployee.isPresent()) {
			Employee employee = optionalEmployee.get();
			employee.setEmail(email);
			employee= repository.save(employee);
			
			EmployeeResponse employeeResponse = new EmployeeResponse();
			employeeResponse.setId(employee.getId());
			employeeResponse.setName(employee.getName());
			employeeResponse.setEmail(employee.getEmail());
			
			ResponseStructure<EmployeeResponse> responseStructure = new ResponseStructure<EmployeeResponse>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Data Updated");
			responseStructure.setData(employeeResponse);
			
			return new ResponseEntity<ResponseStructure<EmployeeResponse>>(responseStructure,HttpStatus.OK);
		}
		throw new EmployeeIdNotFoundException("Employee ID Not Found");
		
	}
	
	public ResponseEntity<ResponseStructure<String>> delete(int id){
		
		Optional<Employee> optionalEmployee = repository.findById(id);
		
		if(optionalEmployee.isPresent()) {
			Employee employee = optionalEmployee.get();
			repository.delete(employee);
			
			ResponseStructure<String> responseStructure = new ResponseStructure<String>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Data Deleted");
			responseStructure.setData("Employee of id "+id+" is deleted");
			
			return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK);
		}
		throw new EmployeeIdNotFoundException("Employee Id Not Found");
		
	}
		
}
