package com.revature.banking.servlets;

import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.google.gson.Gson;
import com.revature.banking.models.Account;
import com.revature.banking.models.Client;
import com.revature.banking.services.SessionManager;

@WebServlet("/accounts")
public class Accounts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			Cookie [] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
			     if ("id".equals(cookie.getName())) {
			          Session session = SessionManager.getSession();
			          Integer id = Integer.valueOf(cookie.getValue());
			          Client client = session.get(Client.class, id);
			          PrintWriter writer = response.getWriter();
			          StringBuilder json = new StringBuilder("{");
			          Gson gson = new Gson();
			          for (Account account : client.getAccounts()) {
			        	  json.append("\"" + account.getName() + "\": ");
			        	  json.append(gson.toJson(account));
			        	  json.append(", ");
			          }
			          json.setLength(json.length() - 2);
			          json.append("}");
			          System.out.println(json.toString());
			          writer.write(json.toString());
			     }
			}
		} catch (Exception e) {
			//TODO
		}

	}
}
