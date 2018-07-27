package com.oocl.jpaPractice.oneToMany.repository;

import com.oocl.jpaPractice.oneToMany.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * @author Dylan Wei
 * @date 2018-07-26 13:52
 */
@Repository("employee")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


}
