package main.java.com.excilys.computerdatabase.pagination;

import java.util.List;

import main.java.com.excilys.computerdatabase.model.Company;
import main.java.com.excilys.computerdatabase.model.Computer;

public class Pagination {
	private Computer cpu;
	private List<Computer> listeCpu;
	private List<Company> listeCpn;
	
	public Computer getCpu() {
		return cpu;
	}
	public void setCpu(Computer cpu) {
		this.cpu = cpu;
	}
	public List<Computer> getListeCpu() {
		return listeCpu;
	}
	public void setListeCpu(List<Computer> listeCpu) {
		this.listeCpu = listeCpu;
	}
	public List<Company> getListeCpn() {
		return listeCpn;
	}
	public void setListeCpn(List<Company> listeCpn) {
		this.listeCpn = listeCpn;
	}
}