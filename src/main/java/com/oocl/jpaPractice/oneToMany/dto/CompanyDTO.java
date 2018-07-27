package com.oocl.jpaPractice.oneToMany.dto;

import com.oocl.jpaPractice.oneToMany.entity.Company;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class CompanyDTO {
    private final Long id;
    private final String name;
    private final List<EmployeeDTO> employees;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public CompanyDTO(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.employees = company.getEmployees().stream().map(employee -> new EmployeeDTO(employee)).collect(Collectors.toList());
    }
}