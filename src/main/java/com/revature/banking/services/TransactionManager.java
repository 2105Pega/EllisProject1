package com.revature.banking.services;

import java.util.List;

import com.revature.banking.models.Account;
import com.revature.banking.models.Transaction;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class TransactionManager {
    public List<Transaction> getTransactionsByAccountID(Integer accountID) {
        Session session = SessionManager.getSession();
        Query<Transaction> query = session.createNamedQuery("Transaction_findByAccountID", Transaction.class);
        query.setParameter("accid", accountID);
        try {
            List<Transaction> returnValue = query.getResultList();
            session.close();
            return returnValue;
        } catch (Exception e) {
            session.close();
            return null;
        }
    }

    public void execute(Transaction transaction) {
        Session session = SessionManager.getSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.save(transaction);
        
        Account source = session.get(Account.class, transaction.getAccountId());

        switch (transaction.getTransactionType()) {
            case DEPOSIT:
                source.setBalance(source.getBalance() + transaction.getAmount());
                session.update(source);
                break;
            case WITHDRAW:
                source.setBalance(source.getBalance() - transaction.getAmount());
                session.update(source);
                break;
            case TRANSFER:
                Account destination = session.get(Account.class, transaction.getDestinationId());
                source.setBalance(source.getBalance() - transaction.getAmount());
                source.setBalance(destination.getBalance() + transaction.getAmount());
                session.update(source);
                session.update(destination);
                break;
            default:
                break;

        }
        tx.commit();
        session.close();
    }
}
