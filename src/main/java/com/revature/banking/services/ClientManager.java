package com.revature.banking.services;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.banking.models.Client;

public class ClientManager {
	public Client getClientByName(String username) {
		Session session = SessionManager.getSession();
		Query<Client> query = session.createNamedQuery("Client_findByUsername",
				Client.class);
		query.setParameter("name", username);
		try {
			Client returnValue = query.getSingleResult();
			session.close();
			return returnValue;
		} catch (NoResultException e) {
			session.close();
			return null;
		}

	}
}
