package com.skilldistillery.onlineshoestore.data;

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

	@Override
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findCustomerById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
