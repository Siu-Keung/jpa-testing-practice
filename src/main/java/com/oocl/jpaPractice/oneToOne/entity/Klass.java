package com.oocl.jpaPractice.oneToOne.entity;

import javax.persistence.*;

/**
 * @author Dylan Wei
 * @date 2018-07-26 21:07
 */
@Entity
public class Klass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @OneToOne(mappedBy = "klass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id")
    private Leader leader;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Leader getLeader() {
        return leader;
    }

    public void setLeader(Leader leader) {
        this.leader = leader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Klass(Long id, String name) {

        this.id = id;
        this.name = name;
    }

    public Klass() {

    }
}
