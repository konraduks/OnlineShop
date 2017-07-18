package com.konraduks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.konraduks.model.User_role;


public interface UserRoleRepository extends JpaRepository<User_role, Long> {

}
