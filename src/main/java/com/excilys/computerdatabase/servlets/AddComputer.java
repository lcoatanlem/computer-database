package com.excilys.computerdatabase.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.exception.NotSuchCompanyException;
import com.excilys.computerdatabase.persistence.dto.CompanyDTO;
import com.excilys.computerdatabase.persistence.dto.ComputerDTO;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;

/**
 * Servlet implementation class AddComputer
 */
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerService cpuServ = ComputerService.CPUSERV;

	private CompanyService cpnServ = CompanyService.CPNSERV;
	private List<CompanyDTO> listCpn = new ArrayList<CompanyDTO>();


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (listCpn.isEmpty()){
			listCpn = cpnServ.listCompanies(0, 43);
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
			ComputerDTO cDto = new ComputerDTO(null,name,null,null,null,null);
			if (!request.getParameter("introduced").isEmpty()){
				cDto.setIntroduced(request.getParameter("introduced"));
			}
			if (!request.getParameter("discontinued").isEmpty()) {
				cDto.setDiscontinued(request.getParameter("discontinued"));
			}
			if (!request.getParameter("company").isEmpty()) {
				System.out.println(request.getParameter("company")+"LOL");
				cDto.setId_cpn(Long.parseLong(request.getParameter("company")));
				cDto.setName_cpn(listCpn.get(Integer.parseInt(request.getParameter("company"))).getName());
			}
			try {
				cpuServ.createComputer(cDto);
				int newTotalEntries = cpuServ.getcPage().getTotalEntries() + 1;
				cpuServ.getcPage().setTotalEntries(newTotalEntries);
			} catch (NotSuchCompanyException e) {
				e.printStackTrace();
			}
		}
		doGet(request, response);
	}

}
