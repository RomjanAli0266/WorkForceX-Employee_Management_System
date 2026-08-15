package com.example.employee_management.controller;

import com.example.employee_management.dto.EmployeeDTO;
import com.example.employee_management.dto.EmployeeRequestDTO;
import com.example.employee_management.entity.Employee;
import com.example.employee_management.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // create employee

    @Operation(
            summary = "Create a new employee",
            description = "Create a new employee and returns the saved employee"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Employee created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid employee data"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Employee already exists"
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO createEmployee(
            @Valid @RequestBody EmployeeRequestDTO request) {
        return employeeService.createEmployee(request);
    }

    // Get all employees with paging and sorting

    @Operation(
            summary = "Get all employees",
            description = "Return employees with pagination and sorting"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Employees retrieved successfully"
            )
    })
    @GetMapping
    public Page<EmployeeDTO> getEmployees(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "5") int size,
                                       @RequestParam(defaultValue = "id") String sortBy,
                                       @RequestParam(defaultValue = "asc") String direction) {
        return employeeService.getEmployees(
                page, size, sortBy, direction);
    }

    // get employee by ID
    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Long id) {

        return employeeService.getEmployeeById(id);
     }


     //update employee

    @Operation(
            summary = "Update an employee",
            description = "Update an existing employee using the employee ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Employee updated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid employee data"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Employee not found"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Email already exists"
            )
    })
    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id,
                                  @Valid @RequestBody EmployeeRequestDTO request) {

        return employeeService.updateEmployeeById(id, request);
    }

    // delete employee

    @Operation(
            summary = "Delete an employee",
            description = "Delete an employee using employee ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Employee deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Employee not found"
            )
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);

    }

    // Search employees by name

    @Operation(
            summary = "Search employees by name",
            description = "Find employees whose names contain the given text"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Employee found successfully"
            )
    })
    @GetMapping("/search")
    public List<EmployeeDTO> searchEmployees(@RequestParam String name) {
        return employeeService.searchEmployeesByName(name);
    }

    // Filter employees by department
    @Operation(
            summary = "Get employees by department",
            description = "Return all employees belonging to the specified department"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Employees retrieved successfully"
            )
    })
    @GetMapping("/department/{department}")
    public List<EmployeeDTO> getEmployeesByDepartment(
            @PathVariable String department) {
        return employeeService.getEmployeesByDepartment(department);
    }

    // Filter + Pagination + Sorting
    @Operation(
            summary = "Filter employees by department",
            description = "Return employees from a department with pagination and sorting"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Employees retrieved successfully"
            )
    })
    @GetMapping("/filter")
    public Page<EmployeeDTO> getEmployeesByDepartment(
            @RequestParam String department,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return employeeService.getEmployeesByDepartment(
                department, page, size, sortBy, direction);
    }
}
