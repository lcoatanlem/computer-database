package com.excilys.computerdatabase.servlets;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.dto.ComputerDto;
import com.excilys.computerdatabase.persistence.mapping.dao.ComputerDaoToDto;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;

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

  private ComputerService cpuServ = ComputerService.getInstance();

  private CompanyService cpnServ = CompanyService.getInstance();

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse. response)
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

    // Attributes initialization
    request.setAttribute("cpuId", request.getParameter("cpuId"));
    request.setAttribute("companies", cpnServ.getcPage().getList());

    Computer cpuReq = cpuServ.showComputer(Long.parseLong(request.getParameter("cpuId")));
    ComputerDto cpuDto = ComputerDaoToDto.getInstance().map(cpuReq);
    // Name input
    String name = cpuDto.getName();
    if (name == null || name.isEmpty()) {
      request.setAttribute("nameInput", "");
    } else {
      request.setAttribute("nameInput", name);
    }

    // Introduced input
    String introduced = cpuDto.getIntroduced();
    if (introduced == null || introduced.isEmpty()) {
      request.setAttribute("introducedInput", "");
    } else {
      request.setAttribute("introducedInput", introduced);
    }

    // Discontinued input
    String discontinued = cpuDto.getDiscontinued();
    if (discontinued == null || discontinued.isEmpty()) {
      request.setAttribute("discontinuedInput", "");
    } else {
      request.setAttribute("discontinuedInput", discontinued);
    }

    // Discontinued input
    String cpnId = cpuDto.getIdCpn();
    if (cpnId == null || cpnId.isEmpty()) {
      request.setAttribute("cpnId", "");
    } else {
      request.setAttribute("cpnIdInput", cpnId);
    }

    this.getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp")
        .forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse. response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
