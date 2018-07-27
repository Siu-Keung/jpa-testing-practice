package com.oocl.jpaPractice.oneToOne.controller;

import com.oocl.jpaPractice.oneToOne.entity.Klass;
import com.oocl.jpaPractice.oneToOne.entity.Leader;
import com.oocl.jpaPractice.oneToOne.repository.LeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Dylan Wei
 * @date 2018-07-26 21:25
 */
@RestController
@RequestMapping("/oneToOne/leaders")
public class LeaderController {
    @Autowired
    private LeaderRepository leaderRepository;

    @GetMapping
    public List<Leader> getAllKlass(){
        return this.leaderRepository.findAll();
    }

    @PostMapping
    public Leader addKlass(@RequestBody Leader leader){
        Klass klass = leader.getKlass();
        klass.setLeader(leader);
        return this.leaderRepository.save(leader);
    }

}
