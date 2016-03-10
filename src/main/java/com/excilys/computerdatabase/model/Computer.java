package main.java.com.excilys.computerdatabase.model;

import java.time.LocalDate;

/**
 * This class is the model for the computers. Attributes are the id(even if it is auto-incremented in the DB), the name of the computer, 
 * the date it was introduced, the date it was discontinued, and the manufacturer (using class Company as soon as there is a foreign key in the DB).
 * The only constructor we do is the one with the name, as soon as a Computer with a null name can't exist.
 * @author lcoatanlem
 */
public class Computer {
	private Long id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private Company manufacturer;
	
	/**
	 * @param name
	 * @throws IllegalArgumentException if name is null
	 */
	public Computer(String name) throws IllegalArgumentException {
		if (name == null){
			throw new IllegalArgumentException();
		} else {
			this.name = name;
		}
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
/**
 * @param name
 * @throws IllegalArgumentException if name is null
 */
	public void setName(String name) throws IllegalArgumentException {
		if (name == null){
			throw new IllegalArgumentException();
		} else {
			this.name = name;
		}
	}
	public LocalDate getIntroduced() {
		return introduced;
	}
	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}
	public LocalDate getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}
	public Company getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(Company manufacturer) {
		this.manufacturer = manufacturer;
	}
}
