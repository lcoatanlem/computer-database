package com.excilys.computerdatabase.servlets;

import com.excilys.computerdatabase.mapping.request.PageRequestMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.IService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DeleteComputer extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Autowired
  @Qualifier("computerService")
  private IService<Computer> computerService;

  @Override
  public void init(ServletConfig config) throws ServletException {

    super.init(config);
    WebApplicationContext springContext = WebApplicationContextUtils
        .getWebApplicationContext(config.getServletContext());
    AutowireCapableBeanFactory beanFactory = springContext.getAutowireCapableBeanFactory();
    beanFactory.autowireBean(this);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    System.out.println(computerService);
    // Delete the computers
    PageRequestMapper.delete(request, computerService);
    // Call for dashboard to get the view again
    response.sendRedirect("/computer-database/dashboard");
  }

}
