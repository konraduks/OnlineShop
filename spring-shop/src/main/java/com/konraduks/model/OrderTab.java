package com.konraduks.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class OrderTab {
	@Id
	@GeneratedValue
	private long id;
	@OneToOne
	private User user;
	@OneToMany(cascade = {CascadeType.ALL})
	private List<CartElement> products;
	private int status = 1; //1 = przyjeto do realizacji, 2 - w trakcie realizacji, 3 - zrealizowano
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<CartElement> getProducts() {
		return products;
	}
	public void setProducts(List<CartElement> products) {
		this.products = products;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "OrderTab [id=" + id + ", user=" + user + ", products=" + products + ", status=" + status + "]";
	}	
	
	
	
}
