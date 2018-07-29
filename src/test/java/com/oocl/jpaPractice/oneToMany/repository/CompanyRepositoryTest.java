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

    @Test
    public void should_save_company_successfully(){
        Company company = new Company("公司1");

        Company result = this.repository.save(company);

        assertThat(this.repository.findAll().size(), is(1));
        assertThat(this.manager.find(Company.class, result.getId()), notNullValue());
    }

    @Test
    public void should_get_company_by_id(){
        Company company1 = manager.persistFlushFind(new Company("公司1"));

        Optional<Company> optional = this.repository.findById(company1.getId());

        assertThat(optional.isPresent(), is(true));
        assertThat(optional.get(), is(company1));
    }

    @Test
    public void should_not_get_company_given_invalid_id(){
        Optional<Company> optional = repository.findById(99999L);

        assertThat(optional.isPresent(), is(false));
    }

    @Test
    public void should_get_true_when_given_id_exists(){
        Company company = this.manager.persistFlushFind(new Company("公司1"));

        boolean existed = this.repository.existsById(company.getId());

        assertThat(existed, is(true));
    }

    @Test
    public void should_get_false_when_given_id_not_exists() {
        boolean existed = this.repository.existsById(999999L);

        assertThat(existed, is(false));
    }

    @Test
    public void should_remove_company_successfully(){
        Company company = this.manager.persistFlushFind(new Company("公司1"));
        assertThat(this.repository.findAll().size(), is(1));

        this.repository.deleteById(company.getId());

        assertThat(this.repository.findAll().size(), is(0));
    }

}