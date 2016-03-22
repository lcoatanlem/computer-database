package com.excilys.computerdatabase.persistence.dto;

public class ComputerDto {

  private String id;
  private String name;
  private String introduced;
  private String discontinued;
  private String idCpn;
  private String nameCpn;

  public ComputerDto() {
  }

  /**
   * Creates a ComputerDto from all the arguments. TODO : Mapping from DAO to DTO
   */
  public ComputerDto(String id, String name, String introduced, String discontinued, String idCpn,
      String nameCpn) {
    this.id = id;
    this.name = name;
    this.introduced = introduced;
    this.discontinued = discontinued;
    this.idCpn = idCpn;
    this.nameCpn = nameCpn;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
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

  public String getIdCpn() {
    return idCpn;
  }

  public void setIdCpn(String idCpn) {
    this.idCpn = idCpn;
  }

  public String getNameCpn() {
    return nameCpn;
  }

  public void setNameCpn(String nameCpn) {
    this.nameCpn = nameCpn;
  }

  @Override
  public String toString() {
    return "ComputerDto [id=" + id + ", name=" + name + ", introduced=" + introduced
        + ", discontinued=" + discontinued + ", idCpn=" + idCpn + ", nameCpn=" + nameCpn + "]";
  }

}
