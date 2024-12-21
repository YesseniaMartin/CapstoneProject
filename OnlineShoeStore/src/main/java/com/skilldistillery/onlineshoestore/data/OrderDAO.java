package com.skilldistillery.onlineshoestore.data;

import com.skilldistillery.jpaonlineshoestore.entities.CustomerOrder;

public interface OrderDAO {
	
	CustomerOrder findOrderById(int id);

	CustomerOrder addOrder(CustomerOrder order);

}
