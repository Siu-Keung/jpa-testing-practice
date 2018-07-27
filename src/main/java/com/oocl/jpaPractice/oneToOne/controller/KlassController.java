package com.oocl.jpaPractice.oneToOne.controller;

import com.oocl.jpaPractice.oneToOne.entity.Klass;
import com.oocl.jpaPractice.oneToOne.entity.Leader;
import com.oocl.jpaPractice.oneToOne.repository.KlassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Dylan Wei
 * @date 2018-07-26 21:52
 */
@RestController
@RequestMapping("/oneToOne")
public class KlassController {
    @Autowired
    private KlassRepository klassRepository;

    @Transactional
    @GetMapping("/klasses")
    public List<Klass> getAllKlasses(){
        return this.klassRepository.findAll();
    }

    @Transactional
    @PostMapping("/klasses")
    public Klass save(@RequestBody Klass klass){
        Leader leader = klass.getLeader();
        if(leader != null)
            leader.setKlass(klass);
        return this.klassRepository.save(klass);
    }

    @Transactional
    @GetMapping("/klasses/{id}")
    public Klass findKlassById(@PathVariable Long id, HttpServletResponse response){
        Optional<Klass> optional = this.klassRepository.findById(id);
        if(!optional.isPresent()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return optional.get();
    }

    @Transactional
    @DeleteMapping("/klasses/{id}")
    public void deleteById(@PathVariable Long id){
        this.klassRepository.deleteById(id);
    }

}
