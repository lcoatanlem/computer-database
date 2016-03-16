package com.excilys.computerdatabase.pagination;

import java.util.List;

public abstract class Pagination<T> {
	private int pageNumber;
	private int pageSize;
	private int totalEntries;

	public Pagination(int pageNumber, int pageSize, int totalEntries) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalEntries = totalEntries;
	}

	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalEntries() {
		return totalEntries;
	}
	public void setTotalEntries(int totalEntries) {
		this.totalEntries = totalEntries;
	}	
	public abstract List<T> getList();
}
