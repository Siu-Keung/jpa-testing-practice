package com.oocl.jpaPractice.oneToMany.controller;

import com.oocl.jpaPractice.oneToMany.entity.Company;
import com.oocl.jpaPractice.oneToMany.repository.CompanyRepository;
import org.apache.tomcat.util.buf.UDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Dylan Wei
 * @date 2018-07-26 14:30
 */
@Transactional
@RequestMapping("/oneToMany/companies")
@RestController
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;


    @GetMapping
    public List<Company> getAllCompanies(){
        return this.companyRepository.findAll();
    }

    @PostMapping
    public Company addCompany(@RequestBody Company company){
        if(company.getEmployees() != null)
            company.getEmployees().stream()
            .forEach(employee -> employee.setCompany(company));
        return this.companyRepository.save(company);
    }

    @GetMapping("/{id}")
    public Company findCompanyById(@PathVariable Long id, HttpServletResponse response){
        Optional<Company> optional = this.companyRepository.findById(id);
        if(!optional.isPresent()){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return optional.get();
    }

    @PatchMapping("/{id}")
    public void updateCompany(@PathVariable Long id, @RequestBody Company company, HttpServletResponse response){
        if(!this.companyRepository.existsById(id)){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        this.companyRepository.save(company);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id, HttpServletResponse response){
        if(!this.companyRepository.existsById(id)){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        this.companyRepository.deleteById(id);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

}
