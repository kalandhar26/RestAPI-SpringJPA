package com.springboot.restapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restapi.model.Employee;
import com.springboot.restapi.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService eService;

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getEmployees(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {

		return new ResponseEntity<List<Employee>>(eService.getEmployees(pageNumber, pageSize), HttpStatus.OK);
	}

	@PostMapping("/employees")
	public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {

		return new ResponseEntity<Employee>(eService.saveEmployee(employee), HttpStatus.CREATED);
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Optional<Employee>> getEmployeeByID(@PathVariable Long id) {

		return new ResponseEntity<Optional<Employee>>(eService.getSingleEmployee(id), HttpStatus.OK);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
		employee.setId(id);
		return new ResponseEntity<Employee>(eService.updateEmployee(employee), HttpStatus.OK);
	}

	@DeleteMapping("/employees")
	public ResponseEntity<HttpStatus> deleteEmployeeById(@RequestParam Long id) {

		eService.deleteEmployee(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/employees/filterByName")
	public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String name) {

		return new ResponseEntity<List<Employee>>(eService.getEmployeeByName(name), HttpStatus.OK);
	}

	@GetMapping("/employees/filterByNameAndLocation")
	public ResponseEntity<List<Employee>> getEmployeeByNameAndLocation(@RequestParam String name,
			@RequestParam String location) {

		return new ResponseEntity<List<Employee>>(eService.getEmployeeByNameAndLocation(name, location), HttpStatus.OK);
	}

	@GetMapping("/employees/filterByKeyword")
	public ResponseEntity<List<Employee>> getEmployeeByKeyword(@RequestParam String name) {
		return new ResponseEntity<List<Employee>>(eService.getEmployeeByKeyword(name),HttpStatus.OK);
	}
	
	@GetMapping("/employees/{name}/{location}")
	public ResponseEntity<List<Employee>> getEmployeeByNameOrLocation(@PathVariable String name, @PathVariable String location) {
		return new ResponseEntity<List<Employee>>(eService.getEmployeeByNameOrLocation(name, location),HttpStatus.OK);
	}
	
	@DeleteMapping("/employees/delete/{name}")
	public ResponseEntity<String> deleteEmployeeByName(@PathVariable String name) {
		return new ResponseEntity<String>(eService.deleteByEmplyeeName(name)+" records deleted",HttpStatus.OK);
	}
}
