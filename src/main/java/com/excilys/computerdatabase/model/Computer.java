package main.java.com.excilys.computerdatabase.model;

import java.time.LocalDate;

/**
 * This class is the model of the computers. Attributes are the name of the computer, the date it was introduced,
 * the date it was discontinued, and the manufacturer (using Company class). We can construct an instance of this
 * object with the name.
 * @author lcoatanlem
 */
public class Computer {
	private Long id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private Company manufacturer;
	
	public Computer(){
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
	 * Setter for name, IllegalArgumentException if name is null
	 * @param name
	 */
	public void setName(String name) {
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
