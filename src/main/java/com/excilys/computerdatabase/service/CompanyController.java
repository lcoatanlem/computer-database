package main.java.com.excilys.computerdatabase.service;

import java.util.ArrayList;
import java.util.List;

import main.java.com.excilys.computerdatabase.model.Company;
import main.java.com.excilys.computerdatabase.persistence.dao.CompanyDAO;

public class CompanyController {
	private CompanyDAO cDAO ;
	private List<Company> liste;
	
	/**
	 * Instantiates the DAO and the List containing all companies.
	 */
	public CompanyController(){
		cDAO = new CompanyDAO();
		liste = new ArrayList<Company>();
	}
	
	/**
	 * Method to list all companies. To paginate, it starts with an index and a number of instances we want.
	 * The method findAll() is called iff the list is empty.
	 * @param begin
	 * @param pagination
	 * @throws IndexOutOfBoundsException
	 * @return a String containing the pretty printing of the instances we want.
	 */
	public String listCompanies(int begin, int pagination) throws IndexOutOfBoundsException{
		if (liste.isEmpty()){
			liste = cDAO.findAll();
		}
		if(begin < 0){
			throw new IndexOutOfBoundsException();
		}
		if(begin > liste.size()){
			throw new IndexOutOfBoundsException();
		}
		if((begin+pagination)>liste.size()){
			pagination = liste.size() - begin;
		}
		String res = "\tCompanies (" + (begin+1) + "-" + (begin+pagination) + "/" + liste.size() + ")\n"
				+ "\t Id \t Name\n\n";
		for (int i = begin; i < begin+pagination; i ++){
			res += "\t " + liste.get(i).getId() + "\t " + liste.get(i).getName() + "\n"; 
		}
		return res;	
	}
}
