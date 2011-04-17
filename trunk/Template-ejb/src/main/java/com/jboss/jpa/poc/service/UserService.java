package com.jboss.jpa.poc.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jboss.jpa.poc.api.IUser;
import com.jboss.jpa.poc.domain.User;

/**
 * Provides service related to User.
 * 
 * @author Munish Gogna
 */

@Stateless
public class UserService implements IUser {

	@PersistenceContext(unitName = "template4all")
	private EntityManager entityManager;

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		return entityManager.createQuery("select user from User as user")
				.getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveUser(User user) {
		if (user != null) {
			entityManager.merge(user);
		}
	}

}
