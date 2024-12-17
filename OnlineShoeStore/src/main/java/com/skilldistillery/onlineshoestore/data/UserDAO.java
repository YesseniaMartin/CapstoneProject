package com.skilldistillery.onlineshoestore.data;

import com.skilldistillery.jpaonlineshoestore.entities.User;

public interface UserDAO {
	User addUser(User user);
	User deleteUser(User user);
	User updateUser(User user);
	User findUserById(int id);

}
