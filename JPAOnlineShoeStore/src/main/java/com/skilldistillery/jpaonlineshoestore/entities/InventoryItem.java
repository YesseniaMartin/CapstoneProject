package com.skilldistillery.jpaonlineshoestore.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="inventory_item")
public class InventoryItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String color;
	
	private double size;
	
	@Column(name="shoe_id")
	private int shoeId;
	
	

	public InventoryItem(int id, String color, double size, int shoeId) {
		super();
		this.id = id;
		this.color = color;
		this.size = size;
		this.shoeId = shoeId;
	}

	public InventoryItem() {
		super();
	}

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

	public int getShoeId() {
		return shoeId;
	}

	public void setShoeId(int shoeId) {
		this.shoeId = shoeId;
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
