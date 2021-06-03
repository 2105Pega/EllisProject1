package com.revature.banking.models;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@org.hibernate.annotations.NamedNativeQuery(name = "Client_getAccountHolders",
query = "select * from clients c " +
 "inner join clients_accounts ca on " +
 "ca.client_id = c.client_id " + 
 "where ca.account_id = :id", resultClass = Client.class)

public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Integer id;
	
	private double balance;
	
	@Column(name = "account_name")
	private String name;
	
    public enum Status {
        PENDING,
        APPROVED,
        CANCELED
    }
    
    @Column(name = "account_status")
    private String status;
	
	@ManyToMany(mappedBy = "accounts")
	transient Set<Client> accountHolders;
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Client> getAccountHolders() {
		return accountHolders;
	}

	public void setAccountHolders(Set<Client> accountHolders) {
		this.accountHolders = accountHolders;
	}
}
