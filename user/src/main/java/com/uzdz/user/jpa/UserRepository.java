package com.uzdz.user.jpa;

import com.uzdz.user.jpa.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * user
 * @author uzdz
 * @date: 2019/7/10 11:37
 * @since 0.1.0
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
