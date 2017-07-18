package com.konraduks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.konraduks.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	User findByUsername(String username);
}
