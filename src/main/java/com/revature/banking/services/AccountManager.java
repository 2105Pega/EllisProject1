package com.revature.banking.services;

import java.util.List;
import javax.persistence.NoResultException;

import com.revature.banking.models.*;

import org.hibernate.query.Query;
import org.hibernate.Session;

public class AccountManager {
    public List<Client> getAccountHolders(Account account) {
        Session session = SessionManager.getSession();
		Query<Client> query = session.createNamedQuery("Client_getAccountHolders",
				Client.class);
		query.setParameter("id", account.getId());
		try {
			List<Client> returnValue = query.getResultList();
			session.close();
			return returnValue;
		} catch (NoResultException e) {
			session.close();
			return null;
		}

    }
}
