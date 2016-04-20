package com.excilys.computerdatabase.servlets;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.Pagination;
import com.excilys.computerdatabase.persistence.mapping.request.PageRequestMapper;
import com.excilys.computerdatabase.service.IService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Dashboard.
 */
@Component
public class Dashboard extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final String ATTR_PAGE = "page";

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

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response).
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    // Get the page from the request
    Pagination page = PageRequestMapper.fromDashboard(request, computerService);
    // Setting page as attribute
    request.setAttribute(ATTR_PAGE, page);
    // Dispatching to the dashboard
    this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request,
        response);
  }
}
