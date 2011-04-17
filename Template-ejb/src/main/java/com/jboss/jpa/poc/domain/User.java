package com.jboss.jpa.poc.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Domain object representing User.
 * 
 * @author Munish Gogna
 * 
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String email;
	private Preference preference;

	@Id
	@TableGenerator(name = "user_seq_gen", table = "sequence", pkColumnName = "seq_name", pkColumnValue = "user_seq_gen", valueColumnName = "seq_count", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "user_seq_gen")
	public Integer getId() {
		return id;
	}

	@Column(name = "user_name")
	public String getName() {
		return name;
	}

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	public Preference getPreference() {
		return preference;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPreference(Preference preferences) {
		this.preference = preferences;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserId: " + id + " Name: " + name + " Email:" + email
				+ " Color: " + preference;
	}

}
