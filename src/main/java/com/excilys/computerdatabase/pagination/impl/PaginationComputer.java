package main.java.com.excilys.computerdatabase.pagination.impl;

import java.util.List;

import main.java.com.excilys.computerdatabase.pagination.Pagination;
import main.java.com.excilys.computerdatabase.persistence.dto.ComputerDTO;

public class PaginationComputer extends Pagination<ComputerDTO> {
	
	private List<ComputerDTO> list;

	@Override
	public List<ComputerDTO> getList() {
		return list;
	}
}