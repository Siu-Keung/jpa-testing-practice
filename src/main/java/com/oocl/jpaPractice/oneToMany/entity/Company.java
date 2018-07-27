package com.oocl.jpaPractice.oneToMany.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Dylan Wei
 * @date 2018-07-26 17:57
 */
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company", fetch = FetchType.LAZY)
    private List<Employee> employees;

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

    public Company(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Company(Long id){
        this.id = id;
    }

    public Company() {
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
