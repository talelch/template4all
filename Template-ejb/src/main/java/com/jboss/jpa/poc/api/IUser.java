package com.jboss.jpa.poc.api;

import java.util.List;

import javax.ejb.Local;

import com.jboss.jpa.poc.domain.User;

/**
 * Interface for our User related Services.
 * 
 * @author Munish Gogna
 * 
 */
@Local
public interface IUser {

	/**
	 * Saves User.
	 * 
	 * @param user
	 *            the user to save
	 */
	void saveUser(final User user);

	/**
	 * Returns list of all users.
	 * 
	 * @return list of user objects
	 */
	List<User> getAll();
}
