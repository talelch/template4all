package com.jboss.jpa.poc.beans;

import java.util.List;

import javax.ejb.EJB;

import org.richfaces.component.html.HtmlDataTable;

import com.jboss.jpa.poc.api.IUser;
import com.jboss.jpa.poc.domain.Preference;
import com.jboss.jpa.poc.domain.User;

/**
 * 
 * @author Munish Gogna
 * 
 */
public class UserServiceBean {

	@EJB
	private IUser iUser;

	private HtmlDataTable dataTable;
	private List<User> users;

	public List<User> getUsers() {
		if (needReload()) {
			users = iUser.getAll();
		}

		return users;
	}

	public String add() {
		final User newUser = new User();
		Preference preference = new Preference();
		preference.setUser(newUser);
		newUser.setPreference(preference);
		users.add(newUser);

		return null;
	}

	public String save() {

		for (User user : users) {
			if (user.getName() != null && !"".equals(user.getName())) {
				iUser.saveUser(user);
			}

		}
		users = null;
		return null;
	}

	private boolean needReload() {
		return users == null || users.size() == 0;
	}

	public HtmlDataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(HtmlDataTable dataTable) {
		this.dataTable = dataTable;
	}

}
