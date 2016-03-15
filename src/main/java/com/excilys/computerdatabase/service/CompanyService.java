package com.excilys.computerdatabase.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.pagination.impl.CompanyPagination;
import com.excilys.computerdatabase.persistence.dao.impl.CompanyDAOImpl;
import com.excilys.computerdatabase.persistence.dto.CompanyDTO;

public class CompanyService {
	private CompanyDAOImpl cDAO ;
	private CompanyPagination cPage;
	
	/**
	 * Instantiates the DAO and the CompanyPage.
	 */
	public CompanyService(){
		cDAO = new CompanyDAOImpl();
		cPage = new CompanyPagination(1L, 1L, 0, 10, cDAO.sizeTable(), new ArrayList<CompanyDTO>());
	}
	
	/**
	 * Method to list all companies. To paginate, it starts with an index and a number of instances we want.
	 * The method findAll() is called iff the list is empty.
	 * @param begin
	 * @param pagination
	 * @return a String containing the pretty printing of the instances we want.
	 */
	public List<CompanyDTO> listCompanies(Long begin, int paging){
		if (begin <= cPage.getTotalEntries() && (begin+paging) > 0){
			
			int pn = cPage.getPageNumber();
			if (begin < cPage.getStartIndex()){
				cPage.setPageNumber(pn-1);
			} else {
				cPage.setPageNumber(pn+1);
			}
			
			cPage.setStartIndex(begin);
			
			cPage.setPageSize(paging);
			
			cPage.getList().clear();
			
			for(Company cpn: cDAO.findAll(begin.intValue(),paging)){
				cPage.setLastIndex(cpn.getId());

				cPage.getList().add(new CompanyDTO(cpn.getId(), cpn.getName()));
			}
			
			if(cPage.getList().size() == 0){
				throw new IndexOutOfBoundsException();
			}
		}
		return cPage.getList();
	}	
}
