package com.revature.banking.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.revature.banking.models.Client;
import com.revature.banking.services.ClientManager;
import com.revature.banking.services.JWT;
import com.revature.banking.services.SHA256;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username-input");
		String passwordHash = SHA256.hash(request.getParameter("password-input"));
		
		ClientManager cm = new ClientManager();
		Client client = cm.getClientByName(username);
		
		if (client == null) {
			try {
				response.getWriter().println("user does not exist");
			} catch (Exception e) {
				//TODO
			}
		} else {
			if (client.getPasswordHash().equals(passwordHash)) {
				String jws = JWT.getJws(username);
				Cookie cookie = new Cookie("jws", jws);
				response.addCookie(cookie);
				cookie = new Cookie("username", username);
				response.addCookie(cookie);
				cookie = new Cookie("id", client.getId().toString());
				response.addCookie(cookie);
				try {
					response.sendRedirect("index.html");
				} catch (Exception e) {
					//TODO
				}
			} else {
				try {
					response.getWriter().println("incorrect password");
				} catch (Exception e) {
					//TODO
				}
			}
		}
	}
}
