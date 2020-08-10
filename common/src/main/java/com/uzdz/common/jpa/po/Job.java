package com.uzdz.common.jpa.po;

import javax.persistence.*;

/**
 * job
 * @author uzdz
 * @date: 2019/7/10 11:42
 * @since 0.1.0
 */
@Entity
@Table(name = "t_job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
