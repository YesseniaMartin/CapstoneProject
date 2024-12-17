package com.skilldistillery.onlineshoestore.data;

import org.springframework.stereotype.Service;

import com.skilldistillery.jpaonlineshoestore.entities.Shoe;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ShoeDAOImpl implements ShoeDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Shoe addShoe(Shoe shoe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shoe deleteShoe(Shoe shoe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shoe updateShoe(Shoe shoe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shoe findShoeByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
