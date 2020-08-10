package com.uzdz.common.jpa;

import com.uzdz.common.jpa.po.Job;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * user
 * @author uzdz
 * @date: 2019/7/10 11:37
 * @since 0.1.0
 */
public interface JobRepository extends JpaRepository<Job, Integer> {
}
