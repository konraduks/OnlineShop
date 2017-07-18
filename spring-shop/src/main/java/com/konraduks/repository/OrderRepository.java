package com.konraduks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.konraduks.model.OrderTab;
import com.konraduks.model.User;

public interface OrderRepository extends JpaRepository<OrderTab, Long>{
	
	OrderTab findById(Long id);

	List<OrderTab> findByUser(User user);
	
	List<OrderTab> findByStatus(int status);
}
