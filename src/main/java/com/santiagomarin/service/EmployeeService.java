package com.santiagomarin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.santiagomarin.exception.ResourceNotFoundException;
import com.santiagomarin.dto.employee.CreateEmployeeRequest;
import com.santiagomarin.dto.employee.EmployeeResponse;
import com.santiagomarin.dto.employee.UpdateEmployeeRequest;
import com.santiagomarin.entities.Employee;
import com.santiagomarin.mapper.EmployeeMapper;
import com.santiagomarin.repository.EmployeeRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class); 
	private EmployeeRepository employeeRepository;
	private EmployeeMapper employeeMapper;
	
	public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
		this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
	}

	@Transactional(readOnly = true)
	public List<EmployeeResponse> getEmployees() {
		log.debug("Fetching all employees from database");
		List<Employee> employees = employeeRepository.findAll();
		log.debug("Found {} employees in database", employees.size());
		return employeeMapper.toResponseList(employees);
	}

	@Transactional(readOnly = true)
	public EmployeeResponse getEmployeeId(Long id) {	
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Employee", "id", id));
		log.debug("Employee found: {} {}", employee.getName(), employee.getLastName());
		return employeeMapper.toResponse(employee);
	}

	@Transactional
	public EmployeeResponse createEmployee(CreateEmployeeRequest request) {
		log.debug("Creating new employee: {} {}, age: {}", request.getName(), request.getLastName(), request.getAge());
		Employee employee = employeeMapper.toEntity(request);
		Employee saveEmployee = employeeRepository.save(employee);
		log.info("Employee created successfully with ID: {}", saveEmployee.getId()); 
		return employeeMapper.toResponse(saveEmployee);
	}

	@Transactional
	public EmployeeResponse updateEmployee(Long id, UpdateEmployeeRequest request) {
		log.debug("Updating employee with ID: {}", id);
		Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "id", id));
		
		employeeMapper.updateEntity(employee, request);
		
		Employee updateEmployee = employeeRepository.save(employee);
		log.info("Employee with ID: {} updated successfully", id);	
		return employeeMapper.toResponse(updateEmployee);
	}

	@Transactional
	public void deleteEmployee(Long id) {
		log.debug("Deleting employee with ID: {}", id);
		Employee employee = employeeRepository.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
		    employeeRepository.delete(employee);
		log.info("Employee with ID: {} deleted successfully", id);
	}

	
	

}
