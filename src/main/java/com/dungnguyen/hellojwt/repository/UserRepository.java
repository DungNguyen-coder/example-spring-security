package com.dungnguyen.hellojwt.repository;

import com.dungnguyen.hellojwt.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
