package com.example.employee_management.dto;

public class EmployeeDTO {

    private Long id;
    private String name;
    private String email;
    private Double salary;
    private String designation;
    private String department;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long id, String name, String email,
                       Double salary, String designation,
                       String department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.designation = designation;
        this.department = department;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
