package com.skilldistillery.onlineshoestore.data;

import org.springframework.stereotype.Service;

import com.skilldistillery.jpaonlineshoestore.entities.Cart;
import com.skilldistillery.jpaonlineshoestore.entities.Shoe;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartDAOImpl implements CartDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Cart addCart(Shoe shoe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart deleteCart(Shoe shoe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart updateCart(Shoe shoe) {
		// TODO Auto-generated method stub
		return null;
	}

}
