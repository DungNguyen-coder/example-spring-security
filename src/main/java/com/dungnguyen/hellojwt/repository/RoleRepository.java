package com.dungnguyen.hellojwt.repository;

import com.dungnguyen.hellojwt.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
