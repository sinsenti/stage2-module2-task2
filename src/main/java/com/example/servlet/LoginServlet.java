package com.example.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet
public class LoginServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    Object user = session.getAttribute("user");
    if (user == null || session == null) {
      resp.sendRedirect("/login.jsp");
    } else {
      resp.sendRedirect("/user/hello.jsp");
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("login");
    String password = req.getParameter("password");

    if (username != null && !password.isEmpty() && password != null && (username == "user" || username == "admin")) {
      HttpSession session = req.getSession(true);
      session.setAttribute("user", username);
      resp.sendRedirect("/user/hello.jsp");
    } else {
      req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

  }
}
