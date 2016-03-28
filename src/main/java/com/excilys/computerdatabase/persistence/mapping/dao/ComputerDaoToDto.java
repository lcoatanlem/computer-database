package com.excilys.computerdatabase.persistence.mapping.dao;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.dto.ComputerDto;

public class ComputerDaoToDto {
  private static final ComputerDaoToDto INSTANCE = new ComputerDaoToDto();

  private ComputerDaoToDto() {
  }

  public static ComputerDaoToDto getInstance() {
    return INSTANCE;
  }

  /**
   * Supposing the Dto is valid, will make a Computer from it. TODO
   * 
   * @param cpu
   *          valid input
   * @return a new Computer
   */
  public ComputerDto map(Computer cpu) {
    // Id
    Long id = (cpu.getId() == null ? null : cpu.getId());
    // name
    String name = cpu.getName();
    // introduced (string)
    String introduced = (cpu.getIntroduced() == null ? null : cpu.getIntroduced().toString());
    // discontinued (string)
    String discontinued = (cpu.getDiscontinued() == null ? null : cpu.getDiscontinued().toString());
    // idCpn
    Long idCpn = (cpu.getManufacturer() == null ? null
        : (cpu.getManufacturer().getId() == null ? null : cpu.getManufacturer().getId()));
    // nameCpn
    String nameCpn = (cpu.getManufacturer() == null ? null
        : (cpu.getManufacturer().getName() == null ? null : cpu.getManufacturer().getName()));
    return ComputerDto.builder().id(id).name(name).introduced(introduced).discontinued(discontinued)
        .idCpn(idCpn).nameCpn(nameCpn).build();
  }

}
