package com.example.employee_management.mapper;

import com.example.employee_management.dto.EmployeeDTO;
import com.example.employee_management.dto.EmployeeRequestDTO;
import com.example.employee_management.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeDTO toDTO(Employee employee) {

        EmployeeDTO dto = new EmployeeDTO();

        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setSalary(employee.getSalary());
        dto.setDesignation(employee.getDesignation());
        dto.setDepartment(employee.getDepartment());

        return dto;
    }

    public Employee toEntity(EmployeeRequestDTO request) {

        Employee employee = new Employee();

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setSalary(request.getSalary());
        employee.setDesignation(request.getDesignation());
        employee.setDepartment(request.getDepartment());

        return employee;
    }
}
