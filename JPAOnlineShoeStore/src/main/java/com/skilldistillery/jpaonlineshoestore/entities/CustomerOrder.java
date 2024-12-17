package com.skilldistillery.jpaonlineshoestore.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "customer_order")
public class CustomerOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "confirmation_number")
	private String confirmationNumber;
	
	
	CustomerOrder(){
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public String getConfirmationNumber() {
		return confirmationNumber;
	}


	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}


	public CustomerOrder(int id, LocalDate date, String confirmationNumber) {
		super();
		this.id = id;
		this.date = date;
		this.confirmationNumber = confirmationNumber;
	}


	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", confirmationNumber=" + confirmationNumber + "]";
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
		CustomerOrder other = (CustomerOrder) obj;
		return id == other.id;
	}
	
	
}
