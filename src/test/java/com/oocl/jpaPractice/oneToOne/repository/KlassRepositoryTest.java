package com.oocl.jpaPractice.oneToOne.repository;

import com.oocl.jpaPractice.oneToOne.entity.Klass;
import com.oocl.jpaPractice.oneToOne.entity.Leader;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
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

    @Test
    public void should_get_all_klass(){
        Klass klass = new Klass();
        klass.setName("一班");
        Klass savedKlass = manager.persistFlushFind(klass);

        List<Klass> queryResult = this.repository.findAll();

        assertThat(queryResult.size(), is(1));
        assertThat(queryResult.get(0), is(savedKlass));
    }

    @Test
    public void should_save_klass(){
        

    }


}