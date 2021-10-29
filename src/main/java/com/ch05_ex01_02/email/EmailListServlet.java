package com.ch05_ex01_02.email;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import javax.servlet.annotation.WebServlet;

import com.ch05_ex01_02.data.UserDB;
import com.ch05_ex01_02.business.User;

@WebServlet(urlPatterns = "/exercises/ch05_ex01_02/emailList")
public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nextUrl = "/exercises/ch05_ex01_02/index.jsp";

        String action = request.getParameter("action");
        if (action == null) {
            action = "join"; // default action
        }
        if (action.equals("join")) {
            nextUrl = "/exercises/ch05_ex01_02/index.jsp";
        } else if (action.equals("add")) {

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");

            User user = new User(firstName, lastName, email);

            String message;
            if (firstName == null || lastName == null || email == null || firstName.isEmpty() || lastName.isEmpty()
                    || email.isEmpty()) {
                message = "Please fill out all three text boxes.";
                nextUrl = "/exercises/ch05_ex01_02/index.jsp";
            } else {
                message = "";
                nextUrl = "/exercises/ch05_ex01_02/thanks.jsp";
                UserDB.insert(user);
            }
            request.setAttribute("user", user);
            request.setAttribute("message", message);
        }
        getServletContext().getRequestDispatcher(nextUrl).forward(request, response);
    }
}