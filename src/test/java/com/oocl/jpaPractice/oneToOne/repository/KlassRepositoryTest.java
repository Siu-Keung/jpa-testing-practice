package com.oocl.jpaPractice.oneToOne.repository;

import com.oocl.jpaPractice.oneToOne.entity.Klass;
import com.oocl.jpaPractice.oneToOne.entity.Leader;
import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Dylan Wei
 * @date 2018-07-27 16:56
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class KlassRepositoryTest {
    @Autowired
    private KlassRepository repository;

    @Autowired
    private TestEntityManager manager;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private List<Klass> klassList;

    @Before
    public void init(){
        Klass klass1 = this.manager.persistFlushFind(new Klass("一班"));
        Klass klass2 = this.manager.persistFlushFind(new Klass("二班"));
        Klass klass3 = this.manager.persistFlushFind(new Klass("三班"));
        this.klassList = new ArrayList<>();
        klassList.add(klass1);
        klassList.add(klass2);
        klassList.add(klass3);
    }

    @After
    public void tearDown() throws Exception {
        this.manager.clear();
    }

    @Test
    public void should_get_all_klass(){
        List<Klass> queryResult = this.repository.findAll();

        assertThat(queryResult.size(), is(3));
        assertThat(queryResult.get(0).getName(), is("一班"));
        assertThat(queryResult.get(1).getName(), is("二班"));
        assertThat(queryResult.get(2).getName(), is("三班"));
    }

    @Test
    public void should_save_klass(){
        Klass klass = new Klass();
        klass.setName("四班");

        Klass result = this.repository.save(klass);

        assertThat(manager.find(Klass.class, result.getId()), is(result));
    }

    @Test
    public void should_get_specific_klass_by_id(){
        Optional<Klass> optional = this.repository.findById(this.klassList.get(1).getId());

        assertThat(optional.isPresent(), is(true));
        assertThat(optional.get(), is(this.klassList.get(1)));
    }

    @Test
    public void should_not_get_klass_when_given_id_not_exist(){
        Optional<Klass> optional = this.repository.findById(99999999L);

        assertThat(optional.isPresent(), is(false));
    }


    @Test
    public void should_remove_klass_successfully_given_valid_id(){
        assertThat(this.repository.findAll().size(), is(3));

        this.repository.deleteById(this.klassList.get(0).getId());

        assertThat(this.repository.findAll().size(), is(2));
        assertThat(this.manager.find(Klass.class, this.klassList.get(0).getId()), nullValue());
    }

    @Test
    public void should_throw_exception_given_id_not_exists(){
        Optional<Klass> optional = this.repository.findById(9999L);
        assertThat(optional.isPresent(), is(false));

        this.expectedException.expect(EmptyResultDataAccessException.class);
        this.repository.deleteById(9999L);
    }

}