package com.skilldistillery.jpaonlineshoestore.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToMany(mappedBy="carts")
	private List<InventoryItem> inventoryItems = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@OneToOne
	@JoinColumn(name="order_id")
	private CustomerOrder order;
	
	
	public Cart() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public List<InventoryItem> getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(List<InventoryItem> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public CustomerOrder getOrder() {
		return order;
	}

	public void setOrder(CustomerOrder order) {
		this.order = order;
	}

	public void addInventoryItem(InventoryItem inventoryItem) {
		if (inventoryItem == null) {
			inventoryItems = new ArrayList<>();
		}
		if (!inventoryItems.contains(inventoryItem) ) {
			inventoryItems.add(inventoryItem);
			inventoryItem.addCart(this);
		}
	}
	
	public void removeInventoryItem(InventoryItem inventoryItem) {
		if (inventoryItems != null && inventoryItems.contains(inventoryItem)) {
			inventoryItems.remove(inventoryItem);
			inventoryItem.removeCart(this);
		}
	}
	

	@Override
	public String toString() {
		return "Cart [id=" + id + "]";
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
		Cart other = (Cart) obj;
		return id == other.id;
	}
	
	
	

}
