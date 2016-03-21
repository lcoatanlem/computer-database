package com.excilys.computerdatabase.service;

import java.time.LocalDate;
import java.util.ArrayList;

import com.excilys.computerdatabase.exception.NoSuchCompanyException;
import com.excilys.computerdatabase.exception.NoSuchComputerException;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.impl.ComputerPagination;
import com.excilys.computerdatabase.persistence.dao.impl.CompanyDaoImpl;
import com.excilys.computerdatabase.persistence.dao.impl.ComputerDaoImpl;
import com.excilys.computerdatabase.persistence.dto.ComputerDto;

public class ComputerService {
	private ComputerDaoImpl cDAO ;
	private ComputerPagination cPage ;
	
	public static final ComputerService CPUSERV;
	static{
		CPUSERV = new ComputerService();
	}
	
	/**
	 * Instantiates the DAO and the list of computers.
	 */
	private ComputerService(){
		cDAO = new ComputerDaoImpl();
		cPage = new ComputerPagination(1, 10, cDAO.countEntries(), new ArrayList<ComputerDto>());
	}
	


	/**
	 * Method to list all computers. To paginate, it starts with an index and a number of instances we want.
	 * The method findAll() is called iff the list is empty. Will return the id and the name only.
	 * @param begin
	 * @param pagination
	 * @throws IndexOutOfBoundsException
	 * @return a String containing the pretty printing of the instances we want.
	 */
	public void listComputers(int begin, int paging){
		if (begin <= cPage.getTotalEntries() && (begin+paging) > 0){
			
			cPage.setPageSize(paging);
			
			cPage.getList().clear();
			
			for(Computer cpu: cDAO.findAll(begin,paging)){
				cPage.getList().add(new ComputerDto(cpu.getId(), cpu.getName(), 
						(cpu.getIntroduced() == null ? null : cpu.getIntroduced().toString()), 
						(cpu.getDiscontinued() == null ? null : cpu.getDiscontinued().toString()), 
						cpu.getManufacturer().getId(), cpu.getManufacturer().getName()));
			}
		}
	}	

	/**
	 * Method to show the details of only one computer. The fields are displayed iff they are not null.
	 * @param id
	 * @return a String containing our pretty printing
	 * @throws NoSuchComputerException
	 */
	public ComputerDto showComputer(Long id) throws NoSuchComputerException{
		Computer cpu = cDAO.find(id);
		return new ComputerDto(cpu.getId(), cpu.getName(), cpu.getIntroduced().toString(), cpu.getDiscontinued().toString(), cpu.getManufacturer().getId(), cpu.getManufacturer().getName());
	}

	/**
	 * Method to create a new computer in DB. Calls CompanyDAO and findCompany(id) to check if the id exists (not null).
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param id
	 * @throws NoSuchCompanyException
	 */
	public void createComputer(ComputerDto cDto) throws NoSuchCompanyException{
		Company manufacturer = null;
		if (cDto.getIdCpn() != null){
			CompanyDaoImpl cpnDAO = new CompanyDaoImpl();
			manufacturer = cpnDAO.find(cDto.getIdCpn());
		}
		Computer cpu = new Computer(cDto.getName());
		cpu.setIntroduced((cDto.getIntroduced() == null ? null : LocalDate.parse(cDto.getIntroduced())));
		cpu.setDiscontinued((cDto.getDiscontinued() == null ? null : LocalDate.parse(cDto.getDiscontinued())));
		cpu.setManufacturer(manufacturer);
		cDAO.create(cpu);
	}

	/**
	 * Method to update a new computer in DB. Calls CompanyDAO and findCompany(id) to check if the id exists (not null).
	 * @param idcpu
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param idcpn
	 * @throws NoSuchCompanyException
	 * @throws NoSuchComputerException
	 */
	public void updateComputer(ComputerDto cDto) throws NoSuchCompanyException, NoSuchComputerException{
		Company manufacturer = null;
		if (cDto.getIdCpn() != null){
			CompanyDaoImpl cpnDAO = new CompanyDaoImpl();
			manufacturer = cpnDAO.find(cDto.getIdCpn());
		}
		Computer cpu = new Computer(cDto.getName());
		cpu.setIntroduced(LocalDate.parse(cDto.getIntroduced()));
		cpu.setDiscontinued(LocalDate.parse(cDto.getDiscontinued()));
		cpu.setManufacturer(manufacturer);
		cDAO.update(cpu);
	}

	/**
	 * Method to delete a computer from a DB.
	 * @param id
	 * @throws NoSuchComputerException
	 */
	public void deleteComputer(Long id) throws NoSuchComputerException{
		cDAO.delete(id);
	}

	public ComputerPagination getcPage() {
		return cPage;
	}

	public void setcPage(ComputerPagination cPage) {
		this.cPage = cPage;
	}
}