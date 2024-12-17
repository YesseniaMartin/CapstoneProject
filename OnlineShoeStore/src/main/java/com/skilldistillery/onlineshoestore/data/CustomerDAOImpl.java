package com.skilldistillery.onlineshoestore.data;

import com.skilldistillery.jpaonlineshoestore.entities.*;
import org.springframework.stereotype.Service;

import com.skilldistillery.jpaonlineshoestore.entities.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerDAOImpl implements CustomerDAO {

	@PersistenceContext
	private EntityManager em;
	Customer updateCustomer = new Customer();
	Customer newCustomer = new Customer();

	@Override
	public Customer addCustomer(Customer customer) {
		em.persist(customer);
		newCustomer = em.find(Customer.class, customer.getId());
		return customer;
	}

	@Override
	public boolean deleteCustomer(Customer customer) {
		boolean wasDeleted = false;

		Customer customerToBeDeleted = em.find(Customer.class, customer);
		if (customerToBeDeleted != null)
			em.remove(customerToBeDeleted);
		wasDeleted = true;

		wasDeleted = !em.contains(customerToBeDeleted);
		return wasDeleted;
	}

	@Override /*make sure to test */
	public Customer updateCustomer(Customer customer) {
		updateCustomer = null;
		String jpql = "UPDATE Customer customer " + "SET Customer.id = :id " + "SET Customer.firstname = :firstname "
				+ "SET Customer.lastname = :lastname " + "SET Customer.email = :email" + "SET Customer.phone = :phone "
				+ "SET Customer.user = :user ";

		Customer updatedCustomer = em.find(Customer.class, customer.getId());

		if (customer.getFirstName() == null) {
			customer.setFirstName(updatedCustomer.getFirstName());
		}
		if (customer.getLastName() == null) {
			customer.setLastName(updatedCustomer.getLastName());
		}
		if (customer.getEmail() == null) {
			customer.setEmail(updatedCustomer.getEmail());
		}
		if (customer.getPhone() == null) {
			customer.setPhone(updatedCustomer.getPhone());
		}
		if (customer.getUser() == null) {
			customer.setUser(updatedCustomer.getUser());
		}
		int updateColumns = em.createQuery(jpql).setParameter("id", customer.getId())
				.setParameter("firstName", customer.getFirstName()).setParameter("lastName", customer.getLastName())
				.setParameter("email", customer.getEmail()).setParameter("phone", customer.getPhone())
				.setParameter("user", customer.getUser()).executeUpdate();

		if (updateColumns > 0) {
			return updatedCustomer;
		} else {

			return updateCustomer;
		}
	}

	@Override
	public Customer findCustomerById(int id) {
		Customer customer = em.find(Customer.class, id);
		
		return customer;
	}

}
