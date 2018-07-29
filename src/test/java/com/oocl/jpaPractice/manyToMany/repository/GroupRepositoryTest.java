package com.oocl.jpaPractice.manyToMany.repository;

import com.oocl.jpaPractice.manyToMany.entity.Group;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Dylan Wei
 * @date 2018-07-29 15:00
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class GroupRepositoryTest {
    @Autowired
    private TestEntityManager manger;
    @Autowired
    private GroupRepository repository;

    @After
    public void reset(){
        this.manger.clear();
    }

    @Test
    public void should_get_all_users(){
        this.manger.persistFlushFind(new Group("group1"));
        this.manger.persistFlushFind(new Group("group2"));

        List<Group> groups = this.repository.findAll();

        assertThat(groups.size(), is(2));
        assertThat(groups.get(0).getName(), is("group1"));
        assertThat(groups.get(1).getName(), is("group2"));
    }

    @Test
    public void should_save_group_successfully(){
        Group group = this.repository.save(new Group("group1"));

        assertThat(this.manger.find(Group.class, group.getId()).getName(), is(group.getName()));
        assertThat(this.repository.findAll().size(), is(1));
    }

    @Test
    public void should_remove_group_successfully(){
        Group group = this.manger.persistFlushFind(new Group("group1"));

        this.repository.deleteById(group.getId());

        assertThat(this.repository.findAll().size(), is(0));
    }

    @Test
    public void should_remove_group_failed(){
        this.manger.persistFlushFind(new Group("group1"));

        try {
            this.repository.deleteById(99999L);
        }catch (Exception e){ }

        assertThat(this.repository.findAll().size(), is(1));
    }


}