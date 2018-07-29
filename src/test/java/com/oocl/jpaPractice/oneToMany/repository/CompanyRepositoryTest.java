package com.oocl.jpaPractice.oneToMany.repository;

import com.oocl.jpaPractice.oneToMany.entity.Company;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

/**
 * @author Dylan Wei
 * @date 2018-07-29 14:39
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CompanyRepositoryTest {
    @Autowired
    private TestEntityManager manager;
    @Autowired
    private CompanyRepository repository;

    @After
    public void reset(){
        this.manager.clear();
    }

    @Test
    public void should_get_all_companies(){
        manager.persistFlushFind(new Company("公司一"));
        manager.persistFlushFind(new Company("公司二"));

        List<Company> companies = repository.findAll();

        assertThat(companies.size(), is(2));
        assertThat(companies.get(0).getName(), is("公司一"));
        assertThat(companies.get(1).getName(), is("公司二"));
    }



}