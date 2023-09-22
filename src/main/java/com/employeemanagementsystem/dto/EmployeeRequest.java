package com.employeemanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {
	private String name;
	private String email;
	private String password;
}
