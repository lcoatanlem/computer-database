package com.excilys.computerdatabase.persistence.mapping.dto;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Company.CompanyBuilder;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.dto.ComputerDto;

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

    Computer cpu = new Computer();

    // Id
    Long id = (cpuDto.getId() == null ? null : Long.parseLong(cpuDto.getId()));
    cpu.setId(id);

    // Name
    String name = cpuDto.getName();
    cpu.setName(name);

    // Introduced
    LocalDate introduced = (cpuDto.getIntroduced() == null ? null
        : LocalDate.parse(cpuDto.getIntroduced()));
    cpu.setIntroduced(introduced);

    // Discontinued
    LocalDate discontinued = (cpuDto.getDiscontinued() == null ? null
        : LocalDate.parse(cpuDto.getDiscontinued()));
    cpu.setDiscontinued(discontinued);

    // Id and name of the Company
    Long idCpn = (cpuDto.getIdCpn() == null ? null : Long.parseLong(cpuDto.getIdCpn()));
    String nameCpn = cpuDto.getNameCpn();
    Company cpn = new CompanyBuilder().id(idCpn).name(nameCpn).build();
    cpu.setManufacturer(cpn);

    return cpu;
  }
}
