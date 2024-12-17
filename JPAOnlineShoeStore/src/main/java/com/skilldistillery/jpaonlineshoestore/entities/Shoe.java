package com.skilldistillery.jpaonlineshoestore.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// this is for the user

@Entity
@Table(name = "shoe")
public class Shoe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "type_id")
	private int type;
	
	@Column(name = "brand_id")
	private int brand;
	
	Shoe(){
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getBrand() {
		return brand;
	}

	public void setBrand(int brand) {
		this.brand = brand;
	}

	public Shoe(int id, double price, int type, int brand) {
		super();
		this.id = id;
		this.price = price;
		this.type = type;
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Shoe [id=" + id + ", price=" + price + ", type=" + type + ", brand=" + brand + "]";
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
		Shoe other = (Shoe) obj;
		return id == other.id;
	}
	
	
}
