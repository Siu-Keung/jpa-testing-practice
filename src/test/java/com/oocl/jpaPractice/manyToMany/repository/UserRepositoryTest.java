package com.oocl.jpaPractice.manyToMany.repository;

import com.oocl.jpaPractice.manyToMany.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Dylan Wei
 * @date 2018-07-29 15:17
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager manager;
    @Autowired
    private UserRepository repository;

    @Test
    public void should_get_all_users(){
        this.manager.persistFlushFind(new User("User1"));
        this.manager.persistFlushFind(new User("User2"));

        List<User> Users = this.repository.findAll();

        assertThat(Users.size(), is(2));
        assertThat(Users.get(0).getName(), is("User1"));
        assertThat(Users.get(1).getName(), is("User2"));
    }

    @Test
    public void should_save_User_successfully(){
        User User = this.repository.save(new User("User1"));

        assertThat(this.manager.find(User.class, User.getId()).getName(), is(User.getName()));
        assertThat(this.repository.findAll().size(), is(1));
    }

    @Test
    public void should_remove_User_successfully(){
        User User = this.manager.persistFlushFind(new User("User1"));

        this.repository.deleteById(User.getId());

        assertThat(this.repository.findAll().size(), is(0));
    }

    @Test
    public void should_remove_User_failed(){
        this.manager.persistFlushFind(new User("User1"));

        try {
            this.repository.deleteById(99999L);
        }catch (Exception e){ }

        assertThat(this.repository.findAll().size(), is(1));
    }

    @Test
    public void should_get_user_by_id(){
        User user = this.manager.persistFlushFind(new User("user1"));

        Optional<User> optional = this.repository.findById(user.getId());

        assertThat(optional.isPresent(), is(true));
        assertThat(optional.get(), is(user));
    }

    @Test
    public void should_update_user_successfully(){
        User user = this.manager.persistFlushFind(new User("user1"));
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setName("user2");

        this.repository.save(newUser);

        assertThat(this.manager.find(User.class, user.getId()).getName(), is(newUser.getName()));
    }

}