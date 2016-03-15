package main.java.com.excilys.computerdatabase.pagination.impl;

import java.util.List;

import main.java.com.excilys.computerdatabase.pagination.Pagination;
import main.java.com.excilys.computerdatabase.persistence.dto.CompanyDTO;

public class PaginationCompany extends Pagination<CompanyDTO> {
	
	private List<CompanyDTO> list;

	@Override
	public List<CompanyDTO> getList() {
		return list;
	}
}