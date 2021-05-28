package com.revature.banking.models;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
	@Id
	@GeneratedValue(generator="increment")
	@Column(name = "account_id")
	private int id;
	
	private double balance;
	
	@Column(name = "account_name")
	private String name;
	
    public enum Status {
        PENDING,
        APPROVED,
        CANCELED
    }
    
    @Column(name = "account_status")
    private Status status;
	
	@ManyToMany(mappedBy = "accounts")
	Set<Client> accountHolders;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Set<Client> getAccountHolders() {
		return accountHolders;
	}

	public void setAccountHolders(Set<Client> accountHolders) {
		this.accountHolders = accountHolders;
	}
}
