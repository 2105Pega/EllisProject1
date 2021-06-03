package com.revature.banking.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.revature.banking.models.*;
import com.revature.banking.services.AccountManager;
import com.revature.banking.services.SessionManager;
import com.revature.banking.services.TransactionManager;

import org.hibernate.Session;

@WebServlet("/account")
public class ViewAccount extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder json = new StringBuilder();
        Integer id = Integer.parseInt(request.getQueryString().split("=")[1]);
        Session session = SessionManager.getSession();
        Account account = session.get(Account.class, id);
        List<Client> accountHolders = new AccountManager().getAccountHolders(account);
        ArrayList<String> clientNames = new ArrayList<>();
        for (Client accountHolder : accountHolders) {
            clientNames.add(accountHolder.getUsername());
        }
        json.append("{ \"accountHolders\": [");
        for (String clientName : clientNames) {
            json.append("\"" + clientName + "\", ");
        }
        json.setLength(json.length() - 2);
        json.append("], \"transactions\": [");

        List<com.revature.banking.models.Transaction> transactions = new TransactionManager().getTransactionsByAccountID(id);
        Gson gson = new Gson();
        for (Transaction transaction : transactions) {
            json.append(gson.toJson(transaction) + ", ");
        }
        json.setLength(json.length() - 2);
        json.append("] }");
        session.close();
        try {
            response.getWriter().print(json.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
