package com.oocl.jpaPractice.oneToOne.repository;

import com.oocl.jpaPractice.oneToOne.entity.Klass;
import com.oocl.jpaPractice.oneToOne.entity.Leader;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
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

    @Before
    public void init(){
        this.manager.persistFlushFind(new Klass("一班"));
        this.manager.persistFlushFind(new Klass("二班"));
        this.manager.persistFlushFind(new Klass("三班"));
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




}