package com.excilys.computerdatabase.servlets;

import com.excilys.computerdatabase.persistence.mapping.request.PageRequestMapper;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteComputer extends HttpServlet {
  
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Delete the computers
    PageRequestMapper.delete(request);
    // Call for dashboard to get the view again
    response.sendRedirect("/computer-database/dashboard");
  }

}
