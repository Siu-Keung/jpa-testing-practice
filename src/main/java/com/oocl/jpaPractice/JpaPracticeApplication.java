package com.oocl.jpaPractice;

import com.oocl.jpaPractice.oneToMany.entity.Company;
import com.oocl.jpaPractice.oneToMany.entity.Employee;
import com.oocl.jpaPractice.oneToMany.repository.CompanyRepository;
import com.oocl.jpaPractice.oneToMany.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class JpaPracticeApplication {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaPracticeApplication.class, args);
	}

}
