package com.excilys.computerdatabase.servlets;

import com.excilys.computerdatabase.mapping.request.PageRequestMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.IService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteComputer extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Autowired
  @Qualifier("computerService")
  private static IService<Computer> computerService;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Delete the computers
    PageRequestMapper.delete(request, computerService);
    // Call for dashboard to get the view again
    response.sendRedirect("/computer-database/dashboard");
  }

}
