package main.java.com.excilys.computerdatabase.model;

import java.time.LocalDate;

/**
 * This class is the model for the computers. Attributes are the id(even if it is auto-incremented in the DB), the name of the computer, 
 * the date it was introduced, the date it was discontinued, and the manufacturer (using class Company as soon as there is a foreign key in the DB).
 * The only constructor we do is the one with the name, as soon as a Computer without a name can't exist.
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
	 * @throws IllegalArgumentException iff name is null
	 */
	public Computer(String name){
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
 * @throws IllegalArgumentException iff name is null
 */
	public void setName(String name){
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Computer)) {
			return false;
		}
		Computer other = (Computer) obj;
		if (discontinued == null) {
			if (other.discontinued != null) {
				return false;
			}
		} else if (!discontinued.equals(other.discontinued)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (introduced == null) {
			if (other.introduced != null) {
				return false;
			}
		} else if (!introduced.equals(other.introduced)) {
			return false;
		}
		if (manufacturer == null) {
			if (other.manufacturer != null) {
				return false;
			}
		} else if (!manufacturer.equals(other.manufacturer)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}
