package com.excilys.computerdatabase.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.exception.NotSuchCompanyException;
import com.excilys.computerdatabase.exception.NotSuchComputerException;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.impl.ComputerPagination;
import com.excilys.computerdatabase.persistence.dao.impl.CompanyDAOImpl;
import com.excilys.computerdatabase.persistence.dao.impl.ComputerDAOImpl;
import com.excilys.computerdatabase.persistence.dto.CompanyDTO;
import com.excilys.computerdatabase.persistence.dto.ComputerDTO;

public class ComputerService {
	private ComputerDAOImpl cDAO ;
	private ComputerPagination cPage ;

	/**
	 * Instantiates the DAO and the list of computers.
	 */
	public ComputerService(){
		cDAO = new ComputerDAOImpl();
		cPage = new ComputerPagination(1L, 1L, 0, 10, cDAO.sizeTable(), new ArrayList<ComputerDTO>());
	}

	/**
	 * Method to list all computers. To paginate, it starts with an index and a number of instances we want.
	 * The method findAll() is called iff the list is empty. Will return the id and the name only.
	 * @param begin
	 * @param pagination
	 * @throws IndexOutOfBoundsException
	 * @return a String containing the pretty printing of the instances we want.
	 */
	public List<ComputerDTO> listComputers(Long begin, int paging){
		if (begin <= cPage.getTotalEntries() && (begin+paging) > 0){
			
			int pagenb = cPage.getPageNumber();
			if (begin < cPage.getStartIndex()){
				cPage.setPageNumber(pagenb-1);
			} else {
				cPage.setPageNumber(pagenb+1);
			}
			
			cPage.setStartIndex(begin);
			
			cPage.setPageSize(paging);
			
			cPage.getList().clear();
			
			for(Computer cpu: cDAO.findAll(begin.intValue(),paging)){
				cPage.setLastIndex(cpu.getId());

				cPage.getList().add(new ComputerDTO(cpu.getId(), cpu.getName(), cpu.getIntroduced(), cpu.getDiscontinued(), cpu.getManufacturer().getId(), cpu.getManufacturer().getName()));
			}
			
			if(cPage.getList().size() == 0){
				throw new IndexOutOfBoundsException();
			}
		}
		return cPage.getList();
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
	public void createComputer(String name, LocalDate introduced, LocalDate discontinued, Long id) throws NotSuchCompanyException{
		Company manufacturer = null;
		if (id != null){
			CompanyDAOImpl cpnDAO = new CompanyDAOImpl();
			manufacturer = cpnDAO.find(id);
		}
		Computer cpu = new Computer(name);
		cpu.setIntroduced(introduced);
		cpu.setDiscontinued(discontinued);
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
	public void updateComputer(Long idcpu, String name, LocalDate introduced, LocalDate discontinued, Long idcpn) throws NotSuchCompanyException, NotSuchComputerException{
		Company manufacturer = null;
		if (idcpn != null){
			CompanyDAOImpl cpnDAO = new CompanyDAOImpl();
			manufacturer = cpnDAO.find(idcpn);
		}
		Computer cpu = new Computer(name);
		cpu.setId(idcpu);
		cpu.setIntroduced(introduced);
		cpu.setDiscontinued(discontinued);
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
}