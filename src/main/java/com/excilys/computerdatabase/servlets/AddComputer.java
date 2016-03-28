package com.excilys.computerdatabase.servlets;

import com.excilys.computerdatabase.pagination.Pagination;
import com.excilys.computerdatabase.persistence.dto.ComputerDto;
import com.excilys.computerdatabase.persistence.mapping.dto.ComputerDtoToDao;
import com.excilys.computerdatabase.persistence.mapping.request.ComputerRequestMapper;
import com.excilys.computerdatabase.persistence.mapping.request.PageRequestMapper;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.validation.ComputerValidator;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
  // TODO logs and check holders / attributes / parameters

  // Params and attributes for AddComputer
  private static final String ATTR_COMPANIES = "companies";
  private static final String ATTR_CPUDTO = "cpuDto";
  private static final String ATTR_ERRORS = "errors";

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response).
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Get the page from the request
    Pagination page = PageRequestMapper.fromAdd(request);
    // Setting page as attribute
    request.setAttribute(ATTR_COMPANIES, page.getCpnList());
    // Dispatching to the dashboard
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
    ComputerDto cpuDto = ComputerRequestMapper.toDto(request,
        PageRequestMapper.fromAdd(request).getCpnList());
    // We create a Map of errors, to link errors with their cause
    Map<String, String> errors = new HashMap<>();
    // Validating the dto, if there is errors will be in errors map
    ComputerValidator.validate(cpuDto, errors);
    if (!errors.isEmpty()) {
      // If there are errors, set the cpuDto and the list of errors as attributes
      log.debug("Name is not valid, launched addcomputer view again with errors.");
      request.setAttribute(ATTR_ERRORS, errors);
      request.setAttribute(ATTR_CPUDTO, cpuDto);
      doGet(request, response);
      return;
    }
    // Everything's ok
    ComputerService.getInstance().createComputer(ComputerDtoToDao.getInstance().map(cpuDto));
    log.info("Added computer : " + cpuDto.getName() + ".");
    response.sendRedirect("/computer-database/dashboard");
  }
}
