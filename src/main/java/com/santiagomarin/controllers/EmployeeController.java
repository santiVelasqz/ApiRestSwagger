package com.santiagomarin.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santiagomarin.dto.employee.CreateEmployeeRequest;
import com.santiagomarin.dto.employee.EmployeeResponse;
import com.santiagomarin.dto.employee.UpdateEmployeeRequest;
import com.santiagomarin.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@Tag(name = "Employees", description = "Employees management APIs")
public class EmployeeController {
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class); 
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping
	@Operation(
			summary = "Get all employees",
		    description = "Returns a list of all employees",
		    responses = {
		        @ApiResponse(
		            responseCode = "200",
		            description = "Successful operation",
		            content = @Content(
		                mediaType = "application/json",
		                schema = @Schema(implementation = EmployeeResponse.class)
		            )
		        )
		    }
	)
	public ResponseEntity<List<EmployeeResponse>> getEmployees() {
		log.info("GET /api/v1/employees - Fetching all employees"); 
		List<EmployeeResponse> response = employeeService.getEmployees();
		log.info("GET /api/v1/employees - Returned {} employees", response.size());
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	@Operation(
			summary = "Get employee by ID",
		    description = "Returns a single employee by their ID",
		    responses = {
		        @ApiResponse(
		            responseCode = "200",
		            description = "Employee found",
		            content = @Content(
		                mediaType = "application/json",
		                schema = @Schema(implementation = EmployeeResponse.class)
		            )
		        ),
		        @ApiResponse(
		            responseCode = "404",
		            description = "Employee not found"
		        )
		    }
	)
	public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
		log.info("GET /api/v1/employees - Fetching all employees"); 
		EmployeeResponse response = employeeService.getEmployeeId(id);
		log.info("GET /api/v1/employees/{} - Employee found: {}", id, response.getName());
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	@Operation(summary = "Create a new employee", description = "Creates a new employee and returns the created employee", responses = {
	        @ApiResponse(
	                responseCode = "201",
	                description = "Employee created successfully",
	                content = @Content(
	                    mediaType = "application/json",
	                    schema = @Schema(implementation = EmployeeResponse.class)
	                )
	            )
	        }
	)
	/*(description = "Datos del empleado",content = @Content(schema = @Schema(implementation = CreateEmployeeRequest.class))) esto swagger nose para que*/
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody CreateEmployeeRequest request) {
		log.info("POST /api/v1/employees - Creating employee: {} {}", request.getName(), request.getLastName());
		EmployeeResponse createEmployee = employeeService.createEmployee(request);
		log.info("POST /api/v1/employees - Employee created with ID: {}", createEmployee.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createEmployee);
    }
	
	@PutMapping("/{id}")
	@Operation(
			summary = "Update an employee",
		    description = "Updates an existing employee by their ID",
		    responses = {
		        @ApiResponse(
		            responseCode = "200",
		            description = "Employee updated successfully",
		            content = @Content(
		                mediaType = "application/json",
		                schema = @Schema(implementation = EmployeeResponse.class)
		            )
		        ),
		        @ApiResponse(
		            responseCode = "404",
		            description = "Employee not found"
		        ),
		        @ApiResponse(
		            responseCode = "400",
		            description = "Invalid input data"
		        )
		    }
	)
	public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id, @Valid @RequestBody UpdateEmployeeRequest request){
		log.info("PUT /api/v1/employees/{} - Updating employee", id);
		EmployeeResponse updateEmployee = employeeService.updateEmployee(id, request);
		log.info("PUT /api/v1/employees/{} - Employee updated successfully", id);
		return ResponseEntity.ok(updateEmployee);
	}
	
	
	@DeleteMapping("/{id}")
	@Operation(
			summary = "Delete an employee",
		    description = "Deletes an employee by their ID",
		    responses = {
		        @ApiResponse(
		            responseCode = "204",
		            description = "Employee deleted successfully"
		        ),
		        @ApiResponse(
		            responseCode = "404",
		            description = "Employee not found"
		        )
		    }
	)
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		log.info("DELETE /api/v1/employees/{} - Deleting employee", id);
		employeeService.deleteEmployee(id);
		log.info("DELETE /api/v1/employees/{} - Employee deleted successfully", id);
		return ResponseEntity.noContent().build();
	}

}
