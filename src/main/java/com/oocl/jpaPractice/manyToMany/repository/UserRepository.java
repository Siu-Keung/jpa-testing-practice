package com.oocl.jpaPractice.manyToMany.repository;

import com.oocl.jpaPractice.manyToMany.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dylan Wei
 * @date 2018-07-27 10:28
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
