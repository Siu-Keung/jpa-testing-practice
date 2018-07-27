package com.oocl.jpaPractice.manyToMany.repository;

import com.oocl.jpaPractice.manyToMany.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GroupRepository extends JpaRepository<Group, Long> {
}
