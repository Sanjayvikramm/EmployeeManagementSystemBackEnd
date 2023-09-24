package com.employeemanagementsystem.customexception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
public class EmployeeIdNotFoundException extends RuntimeException {
	private String message;

}
