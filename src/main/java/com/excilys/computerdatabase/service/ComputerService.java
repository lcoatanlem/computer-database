package com.excilys.computerdatabase.service;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.pagination.impl.ComputerPagination;
import com.excilys.computerdatabase.persistence.dao.impl.CompanyDaoImpl;
import com.excilys.computerdatabase.persistence.dao.impl.ComputerDaoImpl;
import com.excilys.computerdatabase.persistence.dto.ComputerDto;

import java.time.LocalDate;
import java.util.ArrayList;

public class ComputerService {
  private ComputerDaoImpl cpuDao;
  private ComputerPagination cpuPage;

  public static final ComputerService CPUSERV;

  static {
    CPUSERV = new ComputerService();
  }

  /**
   * Instantiates the DAO and the list of computers.
   */
  private ComputerService() {
    cpuDao = ComputerDaoImpl.getInstance();
    cpuPage = new ComputerPagination(1, 10, cpuDao.countEntries(), new ArrayList<ComputerDto>());
  }

  /**
   * Method to list all computers. To paginate, it starts with an index and a
   * number of instances we want. The method findAll() is called iff the list is
   * empty. Will return the id and the name only.
   * 
   * @param offset the beginning of the list
   * @param limit the maximum of elements in the list
   */
  public void listComputers(int offset, int limit) {
    if (offset <= cpuPage.getTotalEntries() && (offset + limit) > 0) {

      cpuPage.setPageSize(limit);

      cpuPage.getList().clear();

      for (Computer cpu : cpuDao.findAll(offset, limit)) {
        cpuPage.getList()
            .add(new ComputerDto(cpu.getId(), cpu.getName(),
                (cpu.getIntroduced() == null ? null : cpu.getIntroduced().toString()),
                (cpu.getDiscontinued() == null ? null : cpu.getDiscontinued().toString()),
                cpu.getManufacturer().getId(), cpu.getManufacturer().getName()));
      }
    }
  }

  /**
   * Method to show the details of only one computer. The fields are displayed
   * iff they are not null.
   * 
   * @param id the id of the computer to show
   * @return a String containing our pretty printing
   */
  public ComputerDto showComputer(Long id) {
    Computer cpu = cpuDao.find(id);
    return new ComputerDto(cpu.getId(), cpu.getName(), cpu.getIntroduced().toString(),
        cpu.getDiscontinued().toString(), cpu.getManufacturer().getId(),
        cpu.getManufacturer().getName());
  }

  /**
   * Method to create a new computer in DB. Calls CompanyDAO and findCompany(id)
   * to check if the id exists (not null).
   * @param cpuDto the computer you to create
   */
  public void createComputer(ComputerDto cpuDto) {
    Company manufacturer = null;
    if (cpuDto.getIdCpn() != null) {
      CompanyDaoImpl cpnDao = CompanyDaoImpl.getInstance();
      manufacturer = cpnDao.find(cpuDto.getIdCpn());
    }
    Computer cpu = new Computer(cpuDto.getName());
    cpu.setIntroduced(
        (cpuDto.getIntroduced() == null ? null : LocalDate.parse(cpuDto.getIntroduced())));
    cpu.setDiscontinued(
        (cpuDto.getDiscontinued() == null ? null : LocalDate.parse(cpuDto.getDiscontinued())));
    cpu.setManufacturer(manufacturer);
    cpuDao.create(cpu);
  }

  /**
   * Method to update a new computer in DB. Calls CompanyDAO and findCompany(id)
   * to check if the id exists (not null).
   * @param cpuDto the computer to update
   */
  public void updateComputer(ComputerDto cpuDto) {
    Company manufacturer = null;
    if (cpuDto.getIdCpn() != null) {
      CompanyDaoImpl cpnDao = CompanyDaoImpl.getInstance();
      manufacturer = cpnDao.find(cpuDto.getIdCpn());
    }
    Computer cpu = new Computer(cpuDto.getName());
    cpu.setIntroduced(LocalDate.parse(cpuDto.getIntroduced()));
    cpu.setDiscontinued(LocalDate.parse(cpuDto.getDiscontinued()));
    cpu.setManufacturer(manufacturer);
    cpuDao.update(cpu);
  }

  /**
   * Method to delete a computer from a DB.
   * 
   * @param id the id of the computer to delete
   */
  public void deleteComputer(Long id) {
    cpuDao.delete(id);
  }

  public ComputerPagination getCpuPage() {
    return cpuPage;
  }

  public void setCpuPage(ComputerPagination cpuPage) {
    this.cpuPage = cpuPage;
  }
}