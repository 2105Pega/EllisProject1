package com.revature.banking.models;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {
	@Id
	@GeneratedValue(generator="increment")
	@Column(name = "client_id")
	private int id;
	
	private String username;
	
	private String passwordHash;
	
	@ManyToMany
	@JoinTable(
			name="clients_accounts",
			joinColumns = @JoinColumn(name = "client_id"),
			inverseJoinColumns = @JoinColumn(name = "account_id"))
	Set<Account> accounts;
	
	public Client(String username, String passwordHash) {
		this.username = username;
		this.passwordHash = passwordHash;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
}
