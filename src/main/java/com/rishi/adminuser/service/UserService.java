package com.rishi.adminuser.service;

/**
 * @author raushanr
 *
 */

import java.util.List;

import com.rishi.adminuser.entity.User;
	
public interface UserService {
	
	User findById(Long id);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserById(Long id);

	void deleteAllUsers();

	List<User> findAllUsers();
}