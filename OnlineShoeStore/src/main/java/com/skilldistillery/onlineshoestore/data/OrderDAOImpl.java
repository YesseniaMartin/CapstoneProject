package com.skilldistillery.onlineshoestore.data;

import org.springframework.stereotype.Service;

import com.skilldistillery.jpaonlineshoestore.entities.CustomerOrder;
import com.skilldistillery.jpaonlineshoestore.entities.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderDAOImpl implements OrderDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public CustomerOrder findOrderById(int id) {
		CustomerOrder order = em.find(CustomerOrder.class, id);

		return order;
	}
	
	@Override
    public CustomerOrder addOrder(CustomerOrder order) {
        em.persist(order);
        return order;
    }

}
