package com.konraduks.cart;

import java.util.ArrayList;
import java.util.List;

import com.konraduks.model.CartElement;
import com.konraduks.model.Product;

public class Cart {

	private final List<CartElement> cartElements = new ArrayList<CartElement>();

	public List<CartElement> getCartElements() {
		return cartElements;
	}

	private CartElement findProductByID(Long id) {
		for (CartElement product : this.cartElements) {
			if (product.getProduct().getId() == id) {
				return product;
			}
		}
		return null;
	}

	public void addProduct(Product product, int quantity) {
		CartElement line = this.findProductByID(product.getId());

		if (line == null) {
			line = new CartElement();
			line.setQuantity(0);
			line.setProduct(product);
			cartElements.add(line);
		}
		int newQuantity = line.getQuantity() + quantity;
		if (newQuantity <= 0) {
			cartElements.remove(line);
		} else {
			line.setQuantity(newQuantity);
		}
		System.out.println("Dodano: " + product.toString() + " w ilosci: " + quantity);
	}

	public void updateProduct(Long id, int quantity) {
		CartElement line = this.findProductByID(id);

		if (line != null) {
			if (quantity <= 0) {
				cartElements.remove(line);
			} else {
				line.setQuantity(quantity);
			}
		}
	}

	public void removeProduct(Product product) {		
		CartElement line = this.findProductByID(product.getId());
		System.out.println("Usuwanie produktu: " + line);

		if (line != null) {
			cartElements.remove(line);
		}
	}
}
