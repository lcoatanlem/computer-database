package com.excilys.computerdatabase.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.excilys.computerdatabase.service.ComputerService;

/**
 * Servlet implementation class Dashboard
 */
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger log = Logger.getLogger(Dashboard.class);

	private ComputerService cpuServ = ComputerService.CPUSERV;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int paramNb = 0;
		if (request.getParameter("numPage") != null){
			try{
				paramNb = Integer.parseInt(request.getParameter("numPage"));
				if(paramNb > 0 && paramNb <= ((cpuServ.getcPage().getTotalEntries()/cpuServ.getcPage().getPageSize())+1)){
					cpuServ.getcPage().setPageNumber(paramNb);
				}
			} catch (NumberFormatException e){
				if (request.getParameter("numPage") != null){
					log.info("User tried to change numPage parameter manually to " + request.getParameter("numPage") + ".");
				}
			}
		}


		int paramLim = 0;
		if (request.getParameter("paramLim") != null){
			try{
				paramLim = Integer.parseInt(request.getParameter("limit"));
				if(paramLim == 10 || paramLim == 50 || paramLim == 100){
					cpuServ.getcPage().setPageNumber(1);
					cpuServ.getcPage().setPageSize(paramLim);
				}
			} catch (NumberFormatException e){
				if (request.getParameter("limit") != null){
					log.info("User tried to change limit parameter manually to " + request.getParameter("limit") + ".");
				}
			}
		}

		cpuServ.listComputers((cpuServ.getcPage().getPageNumber()-1)*cpuServ.getcPage().getPageSize(), cpuServ.getcPage().getPageSize());
		request.setAttribute("cpuPage", cpuServ.getcPage());
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
