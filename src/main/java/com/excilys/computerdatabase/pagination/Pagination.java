package com.excilys.computerdatabase.pagination;

import java.util.List;

public abstract class Pagination<T> {
	private Long startIndex;
	private Long lastIndex;
	private int pageNumber;
	private int pageSize;
	private int totalEntries;
	
	public Pagination(Long startIndex, Long lastIndex, int pageNumber, int pageSize, int totalEntries) {
		super();
		this.startIndex = startIndex;
		this.lastIndex = lastIndex;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalEntries = totalEntries;
	}
	
	public Long getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Long startIndex) {
		this.startIndex = startIndex;
	}
	public Long getLastIndex() {
		return lastIndex;
	}
	public void setLastIndex(Long lastIndex) {
		this.lastIndex = lastIndex;
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
