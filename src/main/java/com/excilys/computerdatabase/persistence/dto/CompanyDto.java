package com.excilys.computerdatabase.persistence.dto;

public class CompanyDto {

  private Long id;
  private String name;

  public CompanyDto(Long id, String name) {
    this.id = id;
    this.name = name;
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
  
  @Override
  public String toString() {
    return "CompanyDto [id=" + id + ", name=" + name + "]";
  }
}
