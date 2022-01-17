package com.springboot.restapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.restapi.model.Employee;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

	// Filter Employee by Name like Search
	List<Employee> findByNameContaining(String keyword, Sort sort);


	// Filter Employee by Name
	List<Employee> findByName(String name);

	// Filter Employee by Name And Location
	List<Employee> findByNameAndLocation(String name, String location);
	
	// JPQL method
	@Query("FROM Employee WHERE name =:name OR location = :location")
	List<Employee> getEmployeesByNameOrLocation(@Param("name")String ename, String location);
	// If Model class variable name and method argument name not matching then we should
	// annotate the method argument with @Param annotation
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Employee WHERE name =:name")
	Integer deleteEmployeeByName(String name);

}
