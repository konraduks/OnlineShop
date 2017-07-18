package com.konraduks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.konraduks.model.Category;
import com.konraduks.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findAll();
	
	Product findById(Long id);
	
	List<Product> findByCategory(Category cat);
	
	@Modifying
	@Query("update Product p set p.name = ?1, p.price = ?2 where p.id = ?3")
	void setProductById(String name, Double price, Long productId);
	
	//@Query("select p from Product p where p.Category = null")
	@Query("select p from Product p where p.category = null")
	List<Product> getWithoutCategory();
	
	@Query("select p from Product p where p.category is not null")
	List<Product> getWithCategory();
}
