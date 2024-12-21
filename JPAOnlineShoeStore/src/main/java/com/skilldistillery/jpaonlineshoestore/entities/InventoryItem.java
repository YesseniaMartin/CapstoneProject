package com.skilldistillery.jpaonlineshoestore.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="inventory_item")
public class InventoryItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String color;
	
	private double size;
	
	@ManyToOne
	@JoinColumn(name="shoe_id")
	private Shoe shoeId;
	
	@ManyToMany
	@JoinTable(name="cart_has_inventory_item", 
	joinColumns = @JoinColumn(name="inventory_item_id"),
	inverseJoinColumns = @JoinColumn(name="cart_id"))
	private List<Cart> carts = new ArrayList<>();

	public InventoryItem() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	

	public Shoe getShoeId() {
		return shoeId;
	}

	public void setShoeId(Shoe shoeId) {
		this.shoeId = shoeId;
	}
	
	

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	
	
	public void addCart(Cart cart) {
		if (carts == null) {
			carts = new ArrayList<>();
		}
		if (!carts.contains(cart)) {
			carts.add(cart);
			cart.addInventoryItem(this);
		}
	}
	
	public void removeCart(Cart cart) {
		if (carts != null && carts.contains(cart)) {
			carts.remove(cart);
			cart.removeInventoryItem(this);
		}
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryItem other = (InventoryItem) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "InventoryItem [id=" + id + ", color=" + color + ", size=" + size + ", shoeId=" + shoeId + "]";
	}
	
}
