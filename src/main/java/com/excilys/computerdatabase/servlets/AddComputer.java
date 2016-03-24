package com.excilys.computerdatabase.servlets;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.pagination.impl.ComputerPagination;
import com.excilys.computerdatabase.persistence.dto.ComputerDto;
import com.excilys.computerdatabase.persistence.mapping.dto.ComputerDtoToDao;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.validation.ComputerValidator;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;

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

  private ComputerService cpuServ = ComputerService.getInstance();

  private CompanyService cpnServ = CompanyService.getCpnserv();

  private static final String ATTR_COMPANIES = "companies";

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response).
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Uploading the companies' list
    cpnServ.listCompanies(0, cpnServ.getcPage().getTotalEntries());
    log.info("Companies list initialisation");

    // If the cpuPage doesn't exists
    if (cpuServ.getCpuPage() == null) {
      cpuServ.setCpuPage(new ComputerPagination(1, 10, cpuServ.countEntries(), new ArrayList<>()));
    }

    // Attributes
    request.setAttribute(ATTR_COMPANIES, cpnServ.getcPage().getList());
    request.setAttribute("nameInput", "Computer name");
    request.setAttribute("introducedInput", "Introduced date");
    request.setAttribute("discontinuedInput", "Discontinued date");

    this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request,
        response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response).
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // We put all parameters into a ComputerDto
    // validating every parameter with the Validator (back-end validation)
    ComputerDto cpuDto = new ComputerDto();
    log.info("Creation computer");

    // Name
    String nameRequest = request.getParameter("name").trim();
    String nameRes = ComputerValidator.getInstance().nameIsValid(nameRequest.trim());
    if (nameRes != null) {
      request.setAttribute("nameError", nameRes);
      log.error(nameRes);
    } else {
      cpuDto.setName(nameRequest);
      request.setAttribute("nameError", null);
    }

    // Introduced
    String introducedRequest = request.getParameter("introduced").trim();
    String introducedRes = ComputerValidator.getInstance().introducedIsValid(introducedRequest);
    if (introducedRes != null) {
      request.setAttribute("introducedError", introducedRes);
      log.error(introducedRes);
    } else {
      // null or empty are valid
      if (introducedRequest == null || introducedRequest.isEmpty()) {
        cpuDto.setIntroduced(null);
      } else {
        cpuDto.setIntroduced(introducedRequest);
      }
      request.setAttribute("introducedError", null);
    }

    // Discontinued
    String discontinuedRequest = request.getParameter("discontinued").trim();
    String discontinuedRes = ComputerValidator.getInstance()
        .discontinuedIsValid(discontinuedRequest, introducedRes);
    if (discontinuedRes != null) {
      request.setAttribute("discontinuedError", discontinuedRes);
      log.error(discontinuedRes);
    } else {
      // null or empty are valid
      if (discontinuedRequest == null || discontinuedRequest.isEmpty()) {
        cpuDto.setDiscontinued(null);
      } else {
        cpuDto.setDiscontinued(discontinuedRequest);
      }
      request.setAttribute("discontinuedError", null);
    }

    // Manufacturer is always valid as soon as we have an option list
    // IdCpn
    String idCpn = (request.getParameter("company").isEmpty() ? null
        : request.getParameter("company"));
    if (idCpn != null) {
      Company cpn = cpnServ.getcPage().getList().get(Integer.parseInt(idCpn));
      cpuDto.setIdCpn(cpn.getId().toString());
      cpuDto.setNameCpn(cpn.getName());
    } else {
      cpuDto.setIdCpn(null);
      cpuDto.setNameCpn(null);
    }

    // If all are valid, we add the computer and go back to dashboard
    if (nameRes == null && introducedRes == null && discontinuedRes == null) {
      cpuServ.createComputer(ComputerDtoToDao.getInstance().map(cpuDto));
      cpuServ.getCpuPage().setTotalEntries(cpuServ.countEntries());
      log.info("Added Computer " + cpuDto.getName());
      response.sendRedirect("/computer-database/dashboard");
    } else {
      // Else we reload the view addComputer.jsp with the wrong parameters
      this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp")
          .forward(request, response);
    }
  }
}
