package com.excilys.computerdatabase.servlets;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.impl.ComputerPagination;
import com.excilys.computerdatabase.persistence.mapping.dao.ComputerDaoToDto;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.validation.Validator;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Dashboard.
 */
public class Dashboard extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private int numPage = 1;
  private int pageSize = 10;

  private Logger log = Logger.getLogger(Dashboard.class);

  private ComputerService cpuServ = ComputerService.getInstance();

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response).
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    // Pagination initialization
    if (cpuServ.getCpuPage() == null) {
      // New Pagination with the empty list
      cpuServ.setCpuPage(new ComputerPagination(1, 10, cpuServ.countEntries(), new ArrayList<>()));
    }

    // Checking the new numPage and set it in the page iff valid
    if (Validator.getInstance().numPageIsValid(request.getParameter("numPage"),
        cpuServ.getCpuPage().getTotalEntries(), pageSize)) {
      numPage = Integer.parseInt(request.getParameter("numPage"));
    } else {
      log.info("User tried to change numPage parameter manually to "
          + request.getParameter("numPage") + ".");
    }

    // Checking the new limit and set it in the page iff valid
    if (Validator.getInstance().pageSizeIsValid(request.getParameter("limit"))) {
      pageSize = Integer.parseInt(request.getParameter("limit"));
    } else {
      log.info("User tried to change limit parameter manually to " + request.getParameter("limit")
          + ".");
    }

    // Cleaning the previous list in Pagination
    cpuServ.getCpuPage().getList().clear();
    // Offset
    int offset = (numPage - 1) * pageSize;
    // Limit
    int limit = pageSize;
    // Mapping from cpu to cpuDto for all results from service
    for (Computer cpu : cpuServ.listComputers(offset, limit)) {
      cpuServ.getCpuPage().getList().add(ComputerDaoToDto.getInstance().map(cpu));
    }
    // Setting new CpuPage as attribute and dispatching to the dashboard again
    request.setAttribute("cpuPage", cpuServ.getCpuPage());
    this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request,
        response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse. response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
