package com.excilys.computerdatabase.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.excilys.computerdatabase.exception.NoSuchCompanyException;
import com.excilys.computerdatabase.persistence.dto.CompanyDto;
import com.excilys.computerdatabase.persistence.dto.ComputerDto;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;

/**
 * Servlet implementation class AddComputer
 */
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger log = Logger.getLogger(AddComputer.class);

	private ComputerService cpuServ = ComputerService.CPUSERV;

	private CompanyService cpnServ = CompanyService.CPNSERV;
	private List<CompanyDto> listCpn = new ArrayList<CompanyDto>();

	boolean added = false;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		added = false;
		if (listCpn.isEmpty()){
			listCpn = cpnServ.listCompanies(0, cpnServ.getcPage().getTotalEntries());
			log.info("Companies list initialisation");
		}
		request.setAttribute("companies", listCpn);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("computerName");
		if (!name.isEmpty()) {
			ComputerDto cDto = new ComputerDto(null,name,null,null,null,null);
			if (!request.getParameter("introduced").isEmpty()){
				cDto.setIntroduced(request.getParameter("introduced"));
			}
			if (!request.getParameter("discontinued").isEmpty()) {
				cDto.setDiscontinued(request.getParameter("discontinued"));
			}
			if (!request.getParameter("company").isEmpty()) {
				cDto.setIdCpn(Long.parseLong(request.getParameter("company")));
				cDto.setName_cpn(listCpn.get(Integer.parseInt(request.getParameter("company"))).getName());
			}
			try {
				cpuServ.createComputer(cDto);
				log.info("Added Computer " + cDto.getName());
				int newTotalEntries = cpuServ.getcPage().getTotalEntries() + 1;
				cpuServ.getcPage().setTotalEntries(newTotalEntries);
				added = true;
			} catch (NoSuchCompanyException e) {
				log.error("This situation shouldn't happen, company was not found.");
				throw new RuntimeException(e);
			}
		} else {
			log.info("The name was null, couldn't create a new Computer in Database.");
		}
		if (added){
			this.getServletContext().getRequestDispatcher("/dashboard").forward(request, response);
		}else{
			doGet(request, response);
		}
	}

}
