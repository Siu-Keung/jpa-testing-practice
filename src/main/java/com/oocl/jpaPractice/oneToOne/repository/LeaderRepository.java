package com.oocl.jpaPractice.oneToOne.repository;

import com.oocl.jpaPractice.oneToOne.entity.Leader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dylan Wei
 * @date 2018-07-26 21:47
 */
@Repository
public interface LeaderRepository extends JpaRepository<Leader, Long> {
}
