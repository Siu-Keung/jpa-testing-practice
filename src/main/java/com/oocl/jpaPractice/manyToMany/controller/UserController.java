package com.oocl.jpaPractice.manyToMany.controller;

import com.oocl.jpaPractice.manyToMany.entity.Group;
import com.oocl.jpaPractice.manyToMany.entity.User;
import com.oocl.jpaPractice.manyToMany.repository.GroupRepository;
import com.oocl.jpaPractice.manyToMany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manyToMany/users")
@Transactional
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @PostMapping
    public User save(@RequestBody User user) {
        User result = this.userRepository.save(user);
        return result;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, HttpServletResponse response) {
        this.userRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id, HttpServletResponse response) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return optionalUser.get();
    }

    @PatchMapping("/{id}")
    public void modifyUser(@PathVariable Long id, @RequestBody User user, HttpServletResponse response){
        this.userRepository.save(user);
        if(!this.userRepository.existsById(id)){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        this.userRepository.save(user);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

}
