package com.socially.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SqlUser {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String login;
	private String password;
	// private List<SqlUser> friends;
	private LocalDateTime createdDate;

	public SqlUser() {
		super();
	}
	
	

	public SqlUser(Long id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}


	public SqlUser(String firstName, String lastName, String login, String password,
			LocalDateTime createdDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
		this.createdDate = createdDate;
	}
	
	

	public SqlUser(Long id, String firstName, String lastName, String login, String password,
			LocalDateTime createdDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
		this.createdDate = createdDate;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "SqlUser [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", login=" + login
				+ ", password=" + password + ", createdDate=" + createdDate + "]";
	}

}
