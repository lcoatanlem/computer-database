package com.excilys.computerdatabase.dto;

import java.io.Serializable;

public class ComputerDto implements Serializable {

  private static final long serialVersionUID = -4943256021887881715L;
  
  private Long id;
  private String name;
  private String introduced;
  private String discontinued;
  private Long idCpn;
  private String nameCpn;

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

  public void setNameCpn(String nameCpn) {
    this.nameCpn = nameCpn;
  }

  @Override
  public String toString() {
    return "ComputerDto [id=" + id + ", name=" + name + ", introduced=" + introduced
        + ", discontinued=" + discontinued + ", idCpn=" + idCpn + ", nameCpn=" + nameCpn + "]";
  }
  
  public static Builder builder() {
    return new ComputerDto.Builder();
  }

  public static class Builder {
    private ComputerDto cpuDto;
    
    public Builder() {
      this.cpuDto = new ComputerDto();
    }
    
    public Builder id(Long id) {
      this.cpuDto.id = id;
      return this;
    }
    
    public Builder name(String name) {
      this.cpuDto.name = name;
      return this;
    }
    
    public Builder introduced(String introduced) {
      this.cpuDto.introduced = introduced;
      return this;
    }
    
    public Builder discontinued(String discontinued) {
      this.cpuDto.discontinued = discontinued;
      return this;
    }
    
    public Builder idCpn(Long idCpn) {
      this.cpuDto.idCpn = idCpn;
      return this;
    }
    
    public Builder nameCpn(String nameCpn) {
      this.cpuDto.nameCpn = nameCpn;
      return this;
    }
    
    public ComputerDto build() {
      return this.cpuDto;
    }
  }
}
