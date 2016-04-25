package com.excilys.computerdatabase.servlets;

import com.excilys.computerdatabase.mapping.request.PageRequestMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.Pagination;
import com.excilys.computerdatabase.service.IService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller implementation class Dashboard.
 */
@Controller
@RequestMapping("/dashboard")
public class Dashboard {
  private static final String ATTR_PAGE = "page";

  @Autowired
  @Qualifier("computerService")
  private IService<Computer> computerService;

  /**
   * DoGet of dashboard.
   */
  @RequestMapping(method = RequestMethod.GET)
  public String doGet(Model model, HttpServletRequest request) {
    // Get the page from the request
    Pagination page = PageRequestMapper.fromDashboard(request, computerService);
    // Setting page as attribute
    model.addAttribute(ATTR_PAGE, page);
    // returns the view name
    return "dashboard";
  }
  
}
