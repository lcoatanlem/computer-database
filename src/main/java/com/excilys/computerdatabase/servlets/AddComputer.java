package com.excilys.computerdatabase.servlets;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.dto.ComputerDto;
import com.excilys.computerdatabase.persistence.mapping.dto.ComputerDtoToDao;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;
import org.apache.log4j.Logger;

import java.io.IOException;
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

  boolean added = false;

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response).
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    added = false;
    // If the company list is empty, we upload it
    if (cpnServ.getcPage().getList().isEmpty()) {
      cpnServ.listCompanies(0, cpnServ.getcPage().getTotalEntries());

      log.info("Companies list initialisation");
    }
    request.setAttribute("companies", cpnServ.getcPage().getList());
    this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request,
        response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response).
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // We put all parameters into a ComputerDto
    // validating every parameter with the Validator (back-end validation)
    ComputerDto cpuDto = new ComputerDto();
    log.info("Creation computer");

    // Name
    
    String name = (request.getParameter("computerName").isEmpty() ? null
        : request.getParameter("computerName"));
    cpuDto.setName(name);

    // Introduced
    String introduced = (request.getParameter("introduced").isEmpty() ? null
        : request.getParameter("introduced"));
    cpuDto.setIntroduced(introduced);

    // Discontinued
    String discontinued = (request.getParameter("discontinued").isEmpty() ? null
        : request.getParameter("discontinued"));
    cpuDto.setDiscontinued(discontinued);

    // Manufacturer
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

    log.error(cpuDto.toString());
    ////// try ComputerValidator.validate(cpuDto) ..

    cpuServ.createComputer(ComputerDtoToDao.getInstance().map(cpuDto));

    log.info("Added Computer " + cpuDto.getName());

    this.getServletContext().getRequestDispatcher("/dashboard").forward(request, response);

  }

}
