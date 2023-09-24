package com.employeemanagementsystem.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.employeemanagementsystem.util.ErrorStructure;

@RestControllerAdvice
public class ApplicationExcepttionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> emailNotFoundException(EmailNotFoundException exception){
		
		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatus(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage("data not found");
		errorStructure.setRootCause(exception.getMessage());
		
		return new ResponseEntity<ErrorStructure>(errorStructure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> employeeIdNotFoundException(EmployeeIdNotFoundException exception){
		
		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatus(HttpStatus.NOT_FOUND.value());
		errorStructure.setRootCause(exception.getMessage());
		errorStructure.setMessage("Data Not Found");
		
		return new ResponseEntity<ErrorStructure>(errorStructure,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> excistingEmailException(ExcistingEmailException exception){
		
		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatus(HttpStatus.BAD_REQUEST.value());
		errorStructure.setMessage("Data Already found");
		errorStructure.setRootCause(exception.getMessage());
		
		return new ResponseEntity<ErrorStructure>(errorStructure,HttpStatus.BAD_REQUEST);
	}
	

}
