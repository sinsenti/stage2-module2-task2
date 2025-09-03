package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebFilter("/user/*")
public class AuthFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    Optional<HttpSession> session = Optional.ofNullable(req.getSession(false));
    // HttpSession session = req.getSession(false);
    if (!session.isPresent() || session.get().getAttribute("user") == null) {
      resp.sendRedirect(req.getContextPath() + "/login.jsp");
    } else {
      chain.doFilter(request, response);
    }
  }
}
