package com.employeemanagementsystem.customexception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExcistingEmailException extends RuntimeException {
	String message;
}
