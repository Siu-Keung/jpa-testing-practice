package com.oocl.jpaPractice.oneToOne.repository;

import com.oocl.jpaPractice.oneToOne.entity.Klass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dylan Wei
 * @date 2018-07-26 21:53
 */
@Repository
public interface KlassRepository extends JpaRepository<Klass, Long> {
}
