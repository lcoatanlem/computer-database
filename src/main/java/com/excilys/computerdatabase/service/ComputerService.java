package com.excilys.computerdatabase.service;

import java.util.ArrayList;
import com.excilys.computerdatabase.exception.NotSuchCompanyException;
import com.excilys.computerdatabase.exception.NotSuchComputerException;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.impl.ComputerPagination;
import com.excilys.computerdatabase.persistence.dao.impl.CompanyDAOImpl;
import com.excilys.computerdatabase.persistence.dao.impl.ComputerDAOImpl;
import com.excilys.computerdatabase.persistence.dto.ComputerDTO;

public class ComputerService {
	private ComputerDAOImpl cDAO ;
	private ComputerPagination cPage ;

	/**
	 * Instantiates the DAO and the list of computers.
	 */
	public ComputerService(){
		cDAO = new ComputerDAOImpl();
		cPage = new ComputerPagination(1, 10, cDAO.sizeTable(), new ArrayList<ComputerDTO>());
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
				cPage.getList().add(new ComputerDTO(cpu.getId(), cpu.getName(), cpu.getIntroduced(), cpu.getDiscontinued(), cpu.getManufacturer().getId(), cpu.getManufacturer().getName()));
			}
		}
	}	

	/**
	 * Method to show the details of only one computer. The fields are displayed iff they are not null.
	 * @param id
	 * @return a String containing our pretty printing
	 * @throws NotSuchComputerException
	 */
	public ComputerDTO showComputer(Long id) throws NotSuchComputerException{
		Computer cpu = cDAO.find(id);
		return new ComputerDTO(cpu.getId(), cpu.getName(), cpu.getIntroduced(), cpu.getDiscontinued(), cpu.getManufacturer().getId(), cpu.getManufacturer().getName());
	}

	/**
	 * Method to create a new computer in DB. Calls CompanyDAO and findCompany(id) to check if the id exists (not null).
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param id
	 * @throws NotSuchCompanyException
	 */
	public void createComputer(ComputerDTO cDto) throws NotSuchCompanyException{
		Company manufacturer = null;
		if (cDto.getId_cpn() != null){
			CompanyDAOImpl cpnDAO = new CompanyDAOImpl();
			manufacturer = cpnDAO.find(cDto.getId_cpn());
		}
		Computer cpu = new Computer(cDto.getName());
		cpu.setIntroduced(cDto.getIntroduced());
		cpu.setDiscontinued(cDto.getDiscontinued());
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
	 * @throws NotSuchCompanyException
	 * @throws NotSuchComputerException
	 */
	public void updateComputer(ComputerDTO cDto) throws NotSuchCompanyException, NotSuchComputerException{
		Company manufacturer = null;
		if (cDto.getId_cpn() != null){
			CompanyDAOImpl cpnDAO = new CompanyDAOImpl();
			manufacturer = cpnDAO.find(cDto.getId_cpn());
		}
		Computer cpu = new Computer(cDto.getName());
		cpu.setIntroduced(cDto.getIntroduced());
		cpu.setDiscontinued(cDto.getDiscontinued());
		cpu.setManufacturer(manufacturer);
		cDAO.update(cpu);
	}

	/**
	 * Method to delete a computer from a DB.
	 * @param id
	 * @throws NotSuchComputerException
	 */
	public void deleteComputer(Long id) throws NotSuchComputerException{
		cDAO.delete(id);
	}

	public ComputerPagination getcPage() {
		return cPage;
	}

	public void setcPage(ComputerPagination cPage) {
		this.cPage = cPage;
	}
}