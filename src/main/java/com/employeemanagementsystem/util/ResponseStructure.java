package com.employeemanagementsystem.util;

import java.util.List;

import com.employeemanagementsystem.dto.EmployeeResponse;
import com.employeemanagementsystem.entity.Employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStructure<T> {
private int status;
private String message;
private T data;
private List<EmployeeResponse> listData;
}
