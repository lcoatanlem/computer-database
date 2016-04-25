package com.excilys.computerdatabase.servlets;

import com.excilys.computerdatabase.mapping.request.PageRequestMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.IService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/deletecomputer")
public class DeleteComputer {

  @Autowired
  @Qualifier("computerService")
  private IService<Computer> computerService;

  /**
   * doPost method.
   */
  @RequestMapping(method = RequestMethod.POST)
  public String doPost(Model model, HttpServletRequest request) {
    // Delete the computers
    PageRequestMapper.delete(request, computerService);
    // Call for dashboard to get the view again
    return "redirect:dashboard";
  }

}
