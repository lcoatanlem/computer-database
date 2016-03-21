package com.excilys.computerdatabase.servlets;

import com.excilys.computerdatabase.service.ComputerService;
import org.apache.log4j.Logger;

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

  private Logger log = Logger.getLogger(Dashboard.class);

  private ComputerService cpuServ = ComputerService.CPUSERV;

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response).
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    int paramNb = 0;
    if (request.getParameter("numPage") != null) {
      try {
        paramNb = Integer.parseInt(request.getParameter("numPage"));
        if (paramNb > 0 && paramNb <= ((cpuServ.getCpuPage().getTotalEntries()
            / cpuServ.getCpuPage().getPageSize()) + 1)) {
          cpuServ.getCpuPage().setPageNumber(paramNb);
        }
      } catch (NumberFormatException exn) {
        if (request.getParameter("numPage") != null) {
          log.info("User tried to change numPage parameter manually to "
              + request.getParameter("numPage") + ".");
        }
      }
    }

    int paramLim = 0;
    if (request.getParameter("paramLim") != null) {
      try {
        paramLim = Integer.parseInt(request.getParameter("limit"));
        if (paramLim == 10 || paramLim == 50 || paramLim == 100) {
          cpuServ.getCpuPage().setPageNumber(1);
          cpuServ.getCpuPage().setPageSize(paramLim);
        }
      } catch (NumberFormatException exn) {
        if (request.getParameter("limit") != null) {
          log.info("User tried to change limit parameter manually to "
              + request.getParameter("limit") + ".");
        }
      }
    }

    cpuServ.listComputers(
        (cpuServ.getCpuPage().getPageNumber() - 1) * cpuServ.getCpuPage().getPageSize(),
        cpuServ.getCpuPage().getPageSize());
    request.setAttribute("cpuPage", cpuServ.getCpuPage());
    this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request,
        response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response).
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
