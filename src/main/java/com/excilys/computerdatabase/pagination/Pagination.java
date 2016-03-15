package main.java.com.excilys.computerdatabase.pagination;

import java.util.List;

public abstract class Pagination<T> {
	
	private int lastIndex;
	private int pageNumber;
	private int pageSize;
	private int totalEntries;
	
	public int getLastIndex() {
		return lastIndex;
	}
	public void setLastIndex(int lastIndex) {
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
