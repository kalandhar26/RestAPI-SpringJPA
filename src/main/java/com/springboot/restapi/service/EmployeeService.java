package com.springboot.restapi.service;

import java.util.List;
import java.util.Optional;

import com.springboot.restapi.model.Employee;

public interface EmployeeService {
	
	Integer deleteByEmplyeeName(String name);
	
	List<Employee> getEmployeeByNameOrLocation(String name, String location);
	
	List<Employee> getEmployeeByKeyword(String name);
	
	List<Employee> getEmployees(int pageNumber, int pageSize);
	
	List<Employee> getEmployeeByName(String name);
	
	List<Employee> getEmployeeByNameAndLocation(String name, String location);
	
	Employee saveEmployee(Employee employee);
	
	Optional<Employee> getSingleEmployee(Long id);
	
	Employee updateEmployee(Employee employee);
	
	void deleteEmployee(Long id);
	


}
