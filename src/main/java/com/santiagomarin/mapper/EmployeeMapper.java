package com.santiagomarin.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.santiagomarin.dto.employee.CreateEmployeeRequest;
import com.santiagomarin.dto.employee.EmployeeResponse;
import com.santiagomarin.dto.employee.UpdateEmployeeRequest;
import com.santiagomarin.entities.Employee;

@Component
public class EmployeeMapper {
	
	//convierte CreateEmpoyeeRequest a Employee
	public Employee toEntity(CreateEmployeeRequest request) {
		Employee employee = new Employee();
		employee.setName(request.getName());
		employee.setLastName(request.getLastName());
		employee.setAge(request.getAge());
		return employee;
	}
	
	//Actualiza un Employee existente con los datos del UpdateEmployeeRequest
	public void updateEntity(Employee employee, UpdateEmployeeRequest request) {
		if(request.getName() != null) {
			employee.setName(request.getName());
		}
		if(request.getLastName() != null) {
			employee.setLastName(request.getLastName());
		}
		if(request.getAge() != null) {
			employee.setAge(request.getAge());
		}
	}
	
	
	//Convierte Employee a EmployeeResponse
	
	public EmployeeResponse toResponse(Employee employee) {
		EmployeeResponse response = new EmployeeResponse();
		response.setId(employee.getId());
		response.setName(employee.getName());
		response.setLastName(employee.getLastName());
		response.setAge(employee.getAge());
		return response;
	}
	
	//Convertir una lista de Employee a lista de EmployeeResponse
	public List<EmployeeResponse> toResponseList(List<Employee> employees){
		return employees.stream()
				.map(this::toResponse)
				.collect(Collectors.toList());
	}

}
