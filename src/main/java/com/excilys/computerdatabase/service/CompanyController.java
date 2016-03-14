package main.java.com.excilys.computerdatabase.service;

import main.java.com.excilys.computerdatabase.model.Company;
import main.java.com.excilys.computerdatabase.pagination.Pagination;
import main.java.com.excilys.computerdatabase.persistence.dao.impl.CompanyDAOImpl;

public class CompanyController {
	private CompanyDAOImpl cDAO ;
	
	/**
	 * Instantiates the DAO and the List containing all companies.
	 */
	public CompanyController(){
		cDAO = new CompanyDAOImpl();
	}
	
	/**
	 * Method to list all companies. To paginate, it starts with an index and a number of instances we want.
	 * The method findAll() is called iff the list is empty.
	 * @param begin
	 * @param pagination
	 * @throws IndexOutOfBoundsException
	 * @return a String containing the pretty printing of the instances we want.
	 */
	public String listCompanies(int begin, int paging) throws IndexOutOfBoundsException{
		Pagination page = new Pagination();
		page.setListeCpn(cDAO.findAll(begin,paging));
		if(page.getListeCpn().size() == 0){
			throw new IndexOutOfBoundsException();
		}
		String res = "\tCompanies (" + (begin+1) + "-" + (begin+(page.getListeCpn().size())) + ")\n"
				+ "\t Id \t Name\n\n";
		for (Company elt : page.getListeCpn()){
			res += "\t " + elt.getId() + "\t " + elt.getName() + "\n"; 
		}
		return res;	
	}
}
