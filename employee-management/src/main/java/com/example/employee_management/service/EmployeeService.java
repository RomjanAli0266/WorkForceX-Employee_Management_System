package com.example.employee_management.service;

import com.example.employee_management.dto.EmployeeDTO;
import com.example.employee_management.dto.EmployeeRequestDTO;
import com.example.employee_management.entity.Employee;
import com.example.employee_management.exception.DuplicateEmailException;
import com.example.employee_management.exception.EmployeeNotFoundException;
import com.example.employee_management.mapper.EmployeeMapper;
import com.example.employee_management.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    // create employee
    public EmployeeDTO createEmployee(EmployeeRequestDTO request) {

        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException(
                    "Employee with email already exists: "
                            + request.getEmail()
            );
        }


        Employee employee = employeeMapper.toEntity(request);

        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toDTO(savedEmployee);
    }

    // Get employee by id
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id: " +id
                        )
                );

        return employeeMapper.toDTO(employee);
    }

    // update by id
    public EmployeeDTO updateEmployeeById(Long id, EmployeeRequestDTO request) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id: " + id
                        )
                );

        if (employeeRepository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new DuplicateEmailException(
                    "Employee with email already exists: "
                    + request.getEmail()
            );
        }

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setSalary(request.getSalary());
        employee.setDesignation(request.getDesignation());
        employee.setDepartment(request.getDepartment());

        Employee updatedEmployee = employeeRepository.save(employee);

        return employeeMapper.toDTO(updatedEmployee);
    }

    // delete employee by id
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new EmployeeNotFoundException(
                        "Employee not found with id: " +id
                )
        );

        employeeRepository.delete(employee);
    }

    // search employee by name
    public List<EmployeeDTO> searchEmployeesByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(employeeMapper::toDTO)
                .toList();
    }

    // get employee by page, size, sortBy, direction
    public Page<EmployeeDTO> getEmployees(
            int page,
            int size,
            String sortBy,
            String direction) {

        validatePagination(page, size, direction);
        validateSortField(sortBy);

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return employeeRepository.findAll(pageable)
                .map(employeeMapper::toDTO);
    }

    // get employee by department
    public List<EmployeeDTO> getEmployeesByDepartment(String department) {

        return employeeRepository
                .findByDepartmentIgnoreCase(department)
                .stream()
                .map(employeeMapper::toDTO)
                .toList();
    }

    // get employee by department, page, size, sortBy, direction
    public Page<EmployeeDTO> getEmployeesByDepartment(
            String department,
            int page,
            int size,
            String sortBy,
            String direction) {

        validatePagination(page, size, direction);
        validateSortField(sortBy);

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return employeeRepository
                .findByDepartmentIgnoreCase(department, pageable)
                .map(employeeMapper::toDTO);
    }


    private void validateSortField(String sortBy) {

        List<String> allowedFields = List.of(
                "id",
                "name",
                "email",
                "salary",
                "designation",
                "department"
        );

        if(!allowedFields.contains(sortBy)) {
            throw new IllegalArgumentException(
                    "Invalid sort field: " + sortBy
            );
        }
    }

    private void validatePagination(
            int page,
            int size,
            String direction) {
        if (page < 0) {
            throw new IllegalArgumentException(
                    "Page number cannot be negative"
            );
        }

        if (size <= 0) {
            throw new IllegalArgumentException(
                    "Page size must be greater than 0"
            );
        }

        if (!direction.equalsIgnoreCase("asc")
        && !direction.equalsIgnoreCase("desc")) {

            throw new IllegalArgumentException(
                    "Direction must be either 'asc' or 'desc'"
            );
        }
    }

    public long countEmployeesByDepartment(String department) {
        return employeeRepository.countByDepartmentIgnoreCase(department);
    }

    public Page<EmployeeDTO> searchEmployeesByName(
            String name,
            int page,
            int size,
            String sortBy,
            String direction) {

        validatePagination(page, size, direction);
        validateSortField(sortBy);

        Sort sort= direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return employeeRepository
                .findByNameContainingIgnoreCase(name, pageable)
                .map(employeeMapper::toDTO);
    }

    public Page<EmployeeDTO> searchEmployees(
            String name,
            String department,
            int page,
            int size,
            String sortBy,
            String direction) {

        validatePagination(page, size, direction);
        validateSortField(sortBy);

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                :Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return employeeRepository
                .findByNameContainingIgnoreCaseAndDepartmentIgnoreCase(
                        name, department, pageable)
                .map(employeeMapper::toDTO);
    }


}
