package com.spring.test.domain;

import java.util.Collection;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
@Entity
public class Role {
	@Id
	private String name;
	
	@ManyToMany(mappedBy = "authorities")
	private Collection<User> users;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}
}
