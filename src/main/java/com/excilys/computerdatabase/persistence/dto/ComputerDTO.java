package com.excilys.computerdatabase.persistence.dto;

public class ComputerDTO {
	
	private Long id;
	private String name;
	private String introduced;
	private String discontinued;
	private Long id_cpn;
	private String name_cpn;

	public ComputerDTO(Long id, String name, String introduced, String discontinued, Long id_cpn,
			String name_cpn) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.id_cpn = id_cpn;
		this.name_cpn = name_cpn;
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

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public Long getId_cpn() {
		return id_cpn;
	}

	public void setId_cpn(Long id_cpn) {
		this.id_cpn = id_cpn;
	}

	public String getName_cpn() {
		return name_cpn;
	}

	public void setName_cpn(String name_cpn) {
		this.name_cpn = name_cpn;
	}

	
}
