package com.revature.banking.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.hibernate.*;

import com.google.gson.Gson;
import com.revature.banking.models.Transaction;
import com.revature.banking.services.SessionManager;
import com.revature.banking.services.TransactionManager;

@WebServlet("/transaction")
public class Transactions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String json = request.getReader().readLine();
			Transaction transaction = new Gson().fromJson(json, Transaction.class);
			new TransactionManager().execute(transaction);

			response.getWriter().println("Successful transaction");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
