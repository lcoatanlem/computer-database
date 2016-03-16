package com.excilys.computerdatabase.pagination.impl;

import java.util.List;

import com.excilys.computerdatabase.pagination.Pagination;
import com.excilys.computerdatabase.persistence.dto.ComputerDTO;

public class ComputerPagination extends Pagination<ComputerDTO> {
	
	private List<ComputerDTO> list;

	public ComputerPagination(int pageNumber, int pageSize, int totalEntries, List<ComputerDTO> list) {
		super(pageNumber, pageSize, totalEntries);
		this.list = list;
	}

	@Override
	public List<ComputerDTO> getList() {
		return list;
	}
}