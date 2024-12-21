package com.skilldistillery.onlineshoestore.data;

import com.skilldistillery.jpaonlineshoestore.entities.*;

import java.util.List;

import org.springframework.stereotype.Service;

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

	@Override 
	public Customer updateCustomer(int id, Customer customer) {
		Customer managedCustomer = em.find(Customer.class, id);
		managedCustomer.setFirstName(customer.getFirstName());
		managedCustomer.setLastName(customer.getLastName());
		managedCustomer.setEmail(customer.getEmail());
		managedCustomer.setPhone(customer.getPhone());
		managedCustomer.setUser(customer.getUser());

		return managedCustomer;
		
	}

	@Override
	public Customer findCustomerById(int id) {
		Customer customer = em.find(Customer.class, id);
		
		return customer;
	}

	@Override
	public Customer findCustomerByUserId(int userId) {
		
		String query = "SELECT c FROM Customer c WHERE c.user.id = :uid";
	    Customer customer = null;
		
	    customer = em.createQuery(query, Customer.class)
                .setParameter("uid", userId)
                .getSingleResult();
	    
		return customer;
	}




}
