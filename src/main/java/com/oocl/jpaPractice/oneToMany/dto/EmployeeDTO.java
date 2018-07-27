package com.oocl.jpaPractice.oneToMany.dto;

import com.oocl.jpaPractice.oneToMany.entity.Company;
import com.oocl.jpaPractice.oneToMany.entity.Employee;

import java.time.ZonedDateTime;

/**
 * @author Dylan Wei
 * @date 2018-07-26 20:52
 */
public class EmployeeDTO {
    private Long id;
    private String name;

    private Long companyId;

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.companyId = employee.getCompany().getId();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getCompanyId() {
        return companyId;
    }

}
