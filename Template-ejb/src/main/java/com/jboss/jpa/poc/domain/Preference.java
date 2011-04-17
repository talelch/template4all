package com.jboss.jpa.poc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Domain object representing User Preference.
 * 
 * @author Munish Gogna
 * 
 */
@Entity
@Table(name = "user_preference")
public class Preference implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String color;

	private User user;

	@Id
	@TableGenerator(name = "preference_seq_gen", table = "sequence", pkColumnName = "seq_name", pkColumnValue = "user_pref_seq", valueColumnName = "seq_count", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "preference_seq_gen")
	public Integer getId() {
		return id;
	}

	@OneToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	@Column(name = "color")
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Preference other = (Preference) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Color: " + color;
	}

}
