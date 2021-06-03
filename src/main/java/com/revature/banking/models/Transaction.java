package com.revature.banking.models;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
@org.hibernate.annotations.NamedQuery(name = "Transaction_findByAccountID", query = "from Transaction where accountId = :accid or destinationId = :accid")

public class Transaction {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Integer id;

    public enum Type { WITHDRAW, DEPOSIT, TRANSFER}
    private Type transactionType;

    private double amount;

    private Integer accountId;

    private Integer destinationId;

    public Transaction() {}

    public Transaction(Integer id, Type transactionType, double amount, Integer accountId, Integer destinationId) {
        this.id = id;
        this.transactionType = transactionType;
        this.amount = amount;
        this.accountId = accountId;
        this.destinationId = destinationId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Type getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(Type transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getAccountId() {
        return this.accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getDestinationId() {
        return this.destinationId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }
}