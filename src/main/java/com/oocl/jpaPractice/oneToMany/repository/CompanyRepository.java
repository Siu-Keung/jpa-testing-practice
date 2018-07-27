package com.oocl.jpaPractice.oneToMany.repository;

import com.oocl.jpaPractice.oneToMany.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dylan Wei
 * @date 2018-07-26 18:47
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
