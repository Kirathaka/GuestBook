package com.thbs.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thbs.gb.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String Role);
}
