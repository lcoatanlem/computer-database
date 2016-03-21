package com.excilys.computerdatabase.servlets;

import com.excilys.computerdatabase.persistence.dto.CompanyDto;
import com.excilys.computerdatabase.persistence.dto.ComputerDto;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddComputer.
 */
public class AddComputer extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private Logger log = Logger.getLogger(AddComputer.class);

  private ComputerService cpuServ = ComputerService.CPUSERV;

  private CompanyService cpnServ = CompanyService.CPNSERV;
  private List<CompanyDto> listCpn = new ArrayList<CompanyDto>();

  boolean added = false;

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response).
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    added = false;
    if (listCpn.isEmpty()) {
      listCpn = cpnServ.listCompanies(0, cpnServ.getcPage().getTotalEntries());
      log.info("Companies list initialisation");
    }
    request.setAttribute("companies", listCpn);
    this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request,
        response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response).
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String name = request.getParameter("computerName");
    if (!name.isEmpty()) {
      ComputerDto cpuDto = new ComputerDto(null, name, null, null, null, null);
      if (!request.getParameter("introduced").isEmpty()) {
        cpuDto.setIntroduced(request.getParameter("introduced"));
      }
      if (!request.getParameter("discontinued").isEmpty()) {
        cpuDto.setDiscontinued(request.getParameter("discontinued"));
      }
      if (!request.getParameter("company").isEmpty()) {
        cpuDto.setIdCpn(Long.parseLong(request.getParameter("company")));
        cpuDto
            .setName_cpn(listCpn.get(Integer.parseInt(request.getParameter("company"))).getName());
      }
      cpuServ.createComputer(cpuDto);
      log.info("Added Computer " + cpuDto.getName());
      int newTotalEntries = cpuServ.getCpuPage().getTotalEntries() + 1;
      cpuServ.getCpuPage().setTotalEntries(newTotalEntries);
      added = true;
    } else {
      log.info("The name was null, couldn't create a new Computer in Database.");
    }
    if (added) {
      this.getServletContext().getRequestDispatcher("/dashboard").forward(request, response);
    } else {
      doGet(request, response);
    }
  }

}
