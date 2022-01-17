package com.springboot.restapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.springboot.restapi.model.Employee;
import com.springboot.restapi.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeerepository;

	@Override
	public List<Employee> getEmployees(int PageNumber, int pageSize) {

		Pageable pages = PageRequest.of(PageNumber, pageSize, Direction.DESC, "id", "name");
		return employeerepository.findAll(pages).getContent();
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeerepository.save(employee);

	}

	@Override
	public Optional<Employee> getSingleEmployee(Long id) {

		Optional<Employee> employee = employeerepository.findById(id);

		if (employee.isPresent()) {
			return employee;
		}
		throw new RuntimeException("Employee not found for the id " + id);
	}

	@Override
	public Employee updateEmployee(Employee employee) {

		return employeerepository.save(employee);
	}

	@Override
	public void deleteEmployee(Long id) {

		employeerepository.deleteById(id);
	}

	@Override
	public List<Employee> getEmployeeByName(String name) {

		return employeerepository.findByName(name);
	}

	@Override
	public List<Employee> getEmployeeByNameAndLocation(String name, String location) {

		return employeerepository.findByNameAndLocation(name, location);

	}

	@Override
	public List<Employee> getEmployeeByKeyword(String name) {
		Sort sort = Sort.by(Sort.Direction.ASC, "id");
		return employeerepository.findByNameContaining(name, sort);
	}

	@Override
	public List<Employee> getEmployeeByNameOrLocation(String name, String location) {
		return employeerepository.getEmployeesByNameOrLocation(name, location);
	}

	@Override
	public Integer deleteByEmplyeeName(String name) {
		return employeerepository.deleteEmployeeByName(name);
	}

}
