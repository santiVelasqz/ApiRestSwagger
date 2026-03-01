package com.santiagomarin.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO to create Employees")
public class CreateEmployeeRequest {
	
	
	@Schema(description = "Name", example = "Juan")
	@NotBlank(message = "Name is required")
	@Size(min=2, max=50, message="Name must contain between 2 and 50 chars")
	private String name;
	
	@Schema(description = "Last name", example = "Pérez")
	@NotBlank(message = "Last name is required")
	@Size(min=2, max=50, message="Name must contain between 2 and 50 chars")
	private String lastName;
	
	
	@Schema(description = "Age", example = "18")
	@NotNull(message = "Age is required")
	@Min(value = 18, message = "The employee age must be greater than 18 years")
    @Max(value = 100, message = "The employee age must be less than 100 years")
	private Integer age;
	
	 public CreateEmployeeRequest() {}
	
	public CreateEmployeeRequest(String name, String lastName, Integer age) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	} 
	
	

}
