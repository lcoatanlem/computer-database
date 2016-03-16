package com.excilys.computerdatabase.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.exception.NotSuchCompanyException;
import com.excilys.computerdatabase.persistence.dto.ComputerDTO;
import com.excilys.computerdatabase.service.ComputerService;

/**
 * Servlet implementation class AddComputer
 */
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerService cpuServ = new ComputerService();
	private ComputerDTO cDto;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			cpuServ.createComputer(cDto);
		} catch (NotSuchCompanyException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
