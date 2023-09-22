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

}
