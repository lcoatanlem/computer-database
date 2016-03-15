package com.excilys.computerdatabase.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.exception.NotSuchCompanyException;
import com.excilys.computerdatabase.exception.NotSuchComputerException;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.dao.impl.CompanyDAOImpl;
import com.excilys.computerdatabase.persistence.dao.impl.ComputerDAOImpl;

public class ComputerController {
	private ComputerDAOImpl cDAO ;
	private List<Computer> liste;

	/**
	 * Instantiates the DAO and the list of computers.
	 */
	public ComputerController(){
		cDAO = new ComputerDAOImpl();
		liste = new ArrayList<Computer>();
	}

	/**
	 * Method to list all computers. To paginate, it starts with an index and a number of instances we want.
	 * The method findAll() is called iff the list is empty. Will return the id and the name only.
	 * @param begin
	 * @param pagination
	 * @throws IndexOutOfBoundsException
	 * @return a String containing the pretty printing of the instances we want.
	 */
	public String listComputers(int begin, int pagination) throws IndexOutOfBoundsException{
		liste = cDAO.findAll(begin,pagination);
		if(begin < 0){
			throw new IndexOutOfBoundsException();
		}
		if(begin > liste.size()){
			throw new IndexOutOfBoundsException();
		}
		if((begin+pagination)>liste.size()){
			pagination = liste.size() - begin;
		}
		String res = "\tComputers (" + (begin+1) + "-" + (begin+pagination) + "/" + liste.size() + ")\n"
				+ "\t Id \t Name\n\n";
		for (int i = begin; i < begin+pagination; i ++){
			res += "\t " + liste.get(i).getId() + "\t " + liste.get(i).getName() + "\n"; 
		}
		return res;	
	}

	/**
	 * Method to show the details of only one computer. The fields are displayed iff they are not null.
	 * @param id
	 * @return a String containing our pretty printing
	 * @throws NotSuchComputerException
	 */
	public String showComputer(Long id) throws NotSuchComputerException{
		String res = "Computer " + id + " infos:\n";
		Computer cpu = cDAO.find(id);
		res += "Name: " + cpu.getName() + "\n";
		if (cpu.getIntroduced() != null){
			res += "Introduced: " + cpu.getIntroduced().toString() + "\n";
		}
		if (cpu.getDiscontinued() != null){
			res += "Discontinued: " + cpu.getDiscontinued().toString() + "\n";
		}
		if (cpu.getManufacturer() != null){
			res += "Manufacturer: " + cpu.getManufacturer().getName() + "\n";
		}
		return res;
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