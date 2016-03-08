package com.excilys.computerdatabase.model;

import java.sql.Date;

/**
 * This class is the model of the computers. Attributes are the name of the computer, the date it was introduced,
 * the date it was discontinued, and the manufacturer (using Company class). We can construct an instance of this
 * object with the name, or with all the elements.
 * @author lcoatanlem
 */
public class Computer {
	private String name;
	private Date introduced;
	private Date discontinued;
	private Company manufacturer;
	
	/**
	 * This is the only constructor we will do, as soon as name is the only param which remain mandatory.
	 * @param name
	 */
	public Computer(String name){
		this.name = name;
	}
	
	public Computer(String name, Date introduced, Date discontinued, Company company) {
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.manufacturer = company;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getIntroduced() {
		return introduced;
	}
	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}
	public Date getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}
	public Company getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(Company manufacturer) {
		this.manufacturer = manufacturer;
	}

}
