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

    // Page number checking
    String numPageRes = Validator.getInstance().numPageIsValid(request.getParameter("numPage"),
        cpuServ.getCpuPage().getTotalEntries(), cpuServ.getCpuPage().getPageSize());
    // Limit of entries checking
    String limitRes = Validator.getInstance().pageSizeIsValid(request.getParameter("limit"));
    if (numPageRes == null) {
      // numPage is ok
      cpuServ.getCpuPage().setPageNumber(Integer.parseInt(request.getParameter("numPage")));
      if (limitRes == null) {
        // both are ok
        cpuServ.getCpuPage().setPageSize(Integer.parseInt(request.getParameter("limit")));
      } else {
        // limitRes is wrong
        log.info(limitRes);
      }
    } else {
      // numPage is wrong
      if (limitRes == null) {
        cpuServ.getCpuPage().setPageSize(Integer.parseInt(request.getParameter("limit")));
        cpuServ.getCpuPage().setPageNumber(1);
        log.info(numPageRes);
      } else {
        // both are wrong
        log.info(numPageRes);
        log.info(limitRes);
      }
    }
    
    int numPage = cpuServ.getCpuPage().getPageNumber();
    int pageSize = cpuServ.getCpuPage().getPageSize();

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
