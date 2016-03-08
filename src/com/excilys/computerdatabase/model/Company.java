package com.excilys.computerdatabase.model;

/**
 * This class is the model of the companies. As soon as we don't need to update/delete... There is 
 * only a getter for the name of the company. We may use the constructor of a company with a name, to 
 * fill Computer's manufacturer field.
 * @author lcoatanlem
 */
public class Company {
	private String name;
	
	public Company(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}

}
