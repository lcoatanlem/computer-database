package com.excilys.computerdatabase.controllers;

import com.excilys.computerdatabase.mapping.dto.ComputerDtoToDao;
import com.excilys.computerdatabase.mapping.request.ComputerRequestMapper;
import com.excilys.computerdatabase.mapping.request.PageRequestMapper;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.Pagination;
import com.excilys.computerdatabase.persistence.dto.ComputerDto;
import com.excilys.computerdatabase.service.IService;
import com.excilys.computerdatabase.service.impl.ComputerServiceImpl;
import com.excilys.computerdatabase.validation.ComputerValidator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Servlet implementation class AddComputer.
 */
@Controller
@RequestMapping("/addcomputer")
public class AddComputer {

  private Logger log = Logger.getLogger(AddComputer.class);

  // Params and attributes for AddComputer
  private static final String ATTR_COMPANIES = "companies";
  private static final String ATTR_CPUDTO = "cpuDto";
  private static final String ATTR_ERRORS = "errors";

  @Autowired
  @Qualifier("computerService")
  private IService<Computer> computerService;

  @Autowired
  @Qualifier("companyService")
  private IService<Company> companyService;

  /**
   * doGet method.
   */
  @RequestMapping(method = RequestMethod.GET)
  public String doGet(Model model, HttpServletRequest request) {
    // Get the page from the request
    Pagination page = PageRequestMapper.fromAdd(request, companyService);
    // Setting companies as attribute
    model.addAttribute(ATTR_COMPANIES, page.getCpnList());
    // Dispatching to the addcomputer's view
    return "addComputer";
  }

  /**
   * doPost method.
   */
  @RequestMapping(method = RequestMethod.POST)
  public String doPost(Model model, HttpServletRequest request) {
    // We put all parameters into a ComputerDto
    ComputerDto cpuDto = ComputerRequestMapper.toDto(request,
        PageRequestMapper.fromAdd(request, companyService).getCpnList());
    // We create a Map of errors, to link errors with their cause
    Map<String, String> errors = new HashMap<>();
    // Validating the dto, if there is errors will be in errors map
    ComputerValidator.validate(cpuDto, errors);
    if (!errors.isEmpty()) {
      // If there are errors, set the cpuDto and the list of errors as
      // attributes
      log.debug("There are errors, launched addcomputer view again with errors.");
      request.setAttribute(ATTR_ERRORS, errors);
      request.setAttribute(ATTR_CPUDTO, cpuDto);
      return doGet(model, request);
    }
    // Everything's ok
    ((ComputerServiceImpl) computerService)
        .createComputer(ComputerDtoToDao.getInstance().map(cpuDto));
    log.info("Added computer : " + cpuDto.getName() + ".");
    return "redirect:dashboard";
  }
}
