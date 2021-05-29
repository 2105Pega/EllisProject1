package com.revature.banking.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.hibernate.*;

import com.revature.banking.models.Client;
import com.revature.banking.services.SHA256;
import com.revature.banking.services.SessionManager;

@WebServlet("/signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username-input");
		String password = request.getParameter("password-input");
		Session session = SessionManager.getSession();
		Transaction tx = session.beginTransaction();
		session.save(new Client(username, SHA256.hash(password)));
		tx.commit();
		session.close();
		try {
			response.getWriter().println("Successful account creation");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
