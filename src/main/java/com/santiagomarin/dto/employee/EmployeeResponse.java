package com.santiagomarin.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "DTO to response Employees")
public class EmployeeResponse {
	
	@Schema(description = "Employee ID", example = "1")
    private Long id;
	
	@Schema(description = "Name", example = "Ann")
	private String name;
	
	@Schema(description = "Last name", example = "Northon")
	private String lastName;
	
	
	@Schema(description = "Age", example = "37")
	private Integer age;
	
	public EmployeeResponse() {}

	public EmployeeResponse(Long id, String name,String lastName,Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.age = age;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
