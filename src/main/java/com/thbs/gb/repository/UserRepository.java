package com.thbs.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thbs.gb.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
