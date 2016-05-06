package com.excilys.computerdatabase.mapping.dto;

import com.excilys.computerdatabase.dto.ComputerDto;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

import java.time.LocalDate;

public class ComputerDtoToDao {
  private static final ComputerDtoToDao INSTANCE = new ComputerDtoToDao();

  private ComputerDtoToDao() {
  }

  public static ComputerDtoToDao getInstance() {
    return INSTANCE;
  }

  /**
   * Supposing the Dto is valid, will make a Computer from it.
   * 
   * @param cpuDto
   *          valid input
   * @return a new Computer
   */
  public Computer map(ComputerDto cpuDto) {

    // Id
    Long id = cpuDto.getId();

    // Name
    String name = cpuDto.getName();

    // Introduced
    LocalDate introduced = (cpuDto.getIntroduced() == null ? null
        : LocalDate.parse(cpuDto.getIntroduced()));

    // Discontinued
    LocalDate discontinued = (cpuDto.getDiscontinued() == null ? null
        : LocalDate.parse(cpuDto.getDiscontinued()));

    // Id and name of the Company
    Long idCpn = cpuDto.getIdCpn();
    String nameCpn = cpuDto.getNameCpn();
    Company cpn = new Company.Builder().id(idCpn).name(nameCpn).build();
    
    return Computer.builder(name).id(id).introduced(introduced)
        .discontinued(discontinued).manufacturer(cpn).build();
  }
}
