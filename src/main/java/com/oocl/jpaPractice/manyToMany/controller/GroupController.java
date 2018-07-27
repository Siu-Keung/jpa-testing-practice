package com.oocl.jpaPractice.manyToMany.controller;

import com.oocl.jpaPractice.manyToMany.entity.Group;
import com.oocl.jpaPractice.manyToMany.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/manyToMany/groups")
public class GroupController {
    @Autowired
    private GroupRepository groupRepository;

    @GetMapping
    public List<Group> getAllGroups(){
        return this.groupRepository.findAll();
    }

}
