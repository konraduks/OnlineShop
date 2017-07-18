package com.konraduks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.konraduks.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	Category findById(Long id);
	
	Category findByName(String name);
}
