package com.excilys.computerdatabase.persistence.dto;

public class ComputerDto {

  private Long id;
  private String name;
  private String introduced;
  private String discontinued;
  private Long idCpn;
  private String nameCpn;

  public ComputerDto() {}
  
  /**
   * Creates a ComputerDto from all the arguments.
   * TODO : Mapping from DAO to DTO
   */
  public ComputerDto(Long id, String name, String introduced, String discontinued, Long idCpn,
      String nameCpn) {
    this.id = id;
    this.name = name;
    this.introduced = introduced;
    this.discontinued = discontinued;
    this.idCpn = idCpn;
    this.nameCpn = nameCpn;
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

  public Long getIdCpn() {
    return idCpn;
  }

  public void setIdCpn(Long idCpn) {
    this.idCpn = idCpn;
  }

  public String getNameCpn() {
    return nameCpn;
  }

  public void setName_cpn(String nameCpn) {
    this.nameCpn = nameCpn;
  }

}
