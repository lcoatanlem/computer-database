package com.excilys.computerdatabase.pagination.impl;

import java.util.List;

import com.excilys.computerdatabase.pagination.Pagination;
import com.excilys.computerdatabase.persistence.dto.CompanyDTO;

public class CompanyPagination extends Pagination<CompanyDTO> {
	
	private List<CompanyDTO> list;

	public CompanyPagination(Long startIndex, Long lastIndex, int pageNumber, int pageSize, int totalEntries, List<CompanyDTO> list) {
		super(pageNumber, pageSize, totalEntries);
		this.list = list;
	}

	@Override
	public List<CompanyDTO> getList() {
		return list;
	}
}