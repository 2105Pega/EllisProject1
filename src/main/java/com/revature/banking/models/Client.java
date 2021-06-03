package com.revature.banking.models;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "clients")
@org.hibernate.annotations.NamedQuery(name = "Client_findByUsername", query = "from Client where username = :name")

public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id")
	private Integer id;

	private String username;

	private String passwordHash;

	@ManyToMany
	@JoinTable(name = "clients_accounts", joinColumns = { @JoinColumn(name = "client_id") }, inverseJoinColumns = { @JoinColumn(name = "account_id") })
	Set<Account> accounts;

	public Client() {
	}

	public Client(String username, String passwordHash) {
		this.username = username;
		this.passwordHash = passwordHash;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
}
