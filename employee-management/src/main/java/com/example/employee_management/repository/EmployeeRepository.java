package com.example.employee_management.repository;

import com.example.employee_management.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    List<Employee> findByNameContainingIgnoreCase(String name);

    List<Employee> findByDepartmentIgnoreCase(String department);

    Page<Employee> findByDepartmentIgnoreCase(String department, Pageable pageable);
}
