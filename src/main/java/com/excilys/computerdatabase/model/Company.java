package main.java.com.excilys.computerdatabase.model;

/**
 * This class is the model of the companies. Attributes are id and name, with getters and setters.
 * @author lcoatanlem
 */
public class Company {
	private Long id;
	private String name;
	
	public Company(){}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
