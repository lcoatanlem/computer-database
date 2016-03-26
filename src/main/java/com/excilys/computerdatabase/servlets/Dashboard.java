package com.excilys.computerdatabase.servlets;

import com.excilys.computerdatabase.pagination.Pagination;
import com.excilys.computerdatabase.persistence.mapping.request.PageRequestMapper;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Dashboard.
 */
public class Dashboard extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final String ATTR_PAGE = "page";

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response).
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Get the page from the request
    Pagination page = PageRequestMapper.map(request);
    // Setting page as attribute
    request.setAttribute(ATTR_PAGE, page);
    // Dispatching to the dashboard
    this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request,
        response);
  }
}
