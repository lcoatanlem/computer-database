package com.excilys.computerdatabase.servlets;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.Pagination;
import com.excilys.computerdatabase.persistence.dto.ComputerDto;
import com.excilys.computerdatabase.persistence.mapping.dao.ComputerDaoToDto;
import com.excilys.computerdatabase.persistence.mapping.dto.ComputerDtoToDao;
import com.excilys.computerdatabase.persistence.mapping.request.ComputerRequestMapper;
import com.excilys.computerdatabase.persistence.mapping.request.PageRequestMapper;
import com.excilys.computerdatabase.service.impl.ComputerServiceImpl;
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
 * Servlet implementation class EditComputer.
 */
public class EditComputer extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private Logger log = Logger.getLogger(EditComputer.class);

  // Params and attributes for EditComputer
  private static final String ATTR_COMPANIES = "companies";
  private static final String ATTR_CPUDTO = "cpuDto";
  private static final String ATTR_ERRORS = "errors";

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse.
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Get the page from the request
    Pagination page = PageRequestMapper.fromEdit(request);
    // Get the cpuDto from the request
    ComputerDto cpuDto = ComputerRequestMapper.toDto(request, page.getCpnList());
    // Get the computer from database corresponding to the id
    Computer cpu = ComputerServiceImpl.getInstance().find(cpuDto.getId());
    // And map it to a cpuDto
    cpuDto = ComputerDaoToDto.getInstance().map(cpu);
    // Setting companies and cpuDto as attributes
    request.setAttribute(ATTR_COMPANIES, page.getCpnList());
    request.setAttribute(ATTR_CPUDTO, cpuDto);
    // Dispatching to the editcomputer's view
    this.getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp")
        .forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse.
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // We put all parameters into a ComputerDto
    ComputerDto cpuDto = ComputerRequestMapper.toDto(request,
        PageRequestMapper.fromEdit(request).getCpnList());
    // We create a Map of errors, to link errors with their cause
    Map<String, String> errors = new HashMap<>();
    // Validating the dto, if there is errors will be in errors map
    ComputerValidator.validate(cpuDto, errors);
    if (!errors.isEmpty()) {
      // If there are errors, set the cpuDto and the list of errors as
      // attributes
      log.debug("There are errors, launched editComputer view again with errors.");
      request.setAttribute(ATTR_ERRORS, errors);
      request.setAttribute(ATTR_CPUDTO, cpuDto);
      this.getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp")
      .forward(request, response);
      return;
    }
    // Everything's ok 
    // Externalisation de ComputerService.getInstance()
    ComputerServiceImpl.getInstance().updateComputer(ComputerDtoToDao.getInstance().map(cpuDto));
    log.info("Updated computer : " + cpuDto.getName() + ".");
    response.sendRedirect("/computer-database/dashboard");
  }
}
