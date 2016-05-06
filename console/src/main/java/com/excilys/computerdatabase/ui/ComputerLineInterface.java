package com.excilys.computerdatabase.ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.computerdatabase.dto.ComputerDto;
import com.excilys.computerdatabase.mapping.dto.ComputerDtoToDao;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.IService;
import com.excilys.computerdatabase.service.impl.CompanyServiceImpl;
import com.excilys.computerdatabase.service.impl.ComputerServiceImpl;
import com.excilys.computerdatabase.utils.Query;



public class ComputerLineInterface {
  private int iterCpu;
  private final int paginationCpu = 50;
  private int iterCpn;
  private final int paginationCpn = 10;
  private ComputerServiceImpl cpuController;
  private CompanyServiceImpl cpnController;

  @Autowired
  private IService<Computer> computerService;
  
  @Autowired
  private IService<Company> companyService;
  
  /**
   * TODO : javadoc and total rework.
   */
  public ComputerLineInterface() {
    cpuController = ((ComputerServiceImpl) computerService);
    cpnController = ((CompanyServiceImpl) companyService);
    iterCpu = 0;
    iterCpn = 0;
  }

  private void init() {
    menu();
  }

  private void menu() {
    Scanner sc = new Scanner(System.in);
    int choix = 0;
    System.out.println("\tMenu\n\n 1 List companies\n 2 List computers\n 3 Show computer details\n"
        + " 4 Create a computer\n 5 Update a computer\n 6 Delete a computer\n -1 Exit application\n"
        + "\nVotre choix ?");
    try {
      choix = Integer.valueOf(sc.nextLine());
    } catch (NumberFormatException exn) {
      System.out.println("Not a good entry, "
          + "type an integer between 1 and 6 corresponding to your choice, -1 to exit!");
      menu();
    }
    switch (Integer.valueOf(choix)) {
      case 1:
        listCompanies();
        break;
      case 2:
        listComputers();
        break;
      case 3:
        showComputer();
        break;
      case 4:
        createComputer();
        break;
      case 5:
        updateComputer();
        break;
      case 6:
        deleteComputer();
        break;
      case -1:
        exitApplication();
        break;
      default:
        System.out.println("Not a good entry, "
            + "type an integer between 1 and 6 corresponding to your choice, -1 to exit!");
        menu();
        break;
    }
    sc.close();
  }

  private void listComputers() {
    int choix = 0;
    Scanner sc = new Scanner(System.in);
    Query query = Query.builder().offset(iterCpu).limit(paginationCpu).build();
    try {
      for (Computer cpu : cpuController.list(query)) {
        System.out.println(cpu.getName());
      }
    } catch (IndexOutOfBoundsException exn) {
      System.out.println("OutOfBounds, you need to make another choice");
      if (iterCpu < 0) {
        iterCpu += paginationCpu;
      } else {
        iterCpu -= paginationCpu;
      }
    }

    System.out.println("\n 1 Previous\n 2 Next\n 0 Back to menu\nYour choice ?");
    try {
      choix = Integer.valueOf(sc.nextLine());
    } catch (NumberFormatException exn) {
      System.out.println(
          "Not a good entry, type an integer between 0 and 2 corresponding to your choice!");
      menu();
    }
    switch (Integer.valueOf(choix)) {
      case 0:
        menu();
        break;
      case 1:
        iterCpu -= paginationCpu;
        listComputers();
        break;
      case 2:
        iterCpu += paginationCpu;
        listComputers();
        break;
      default:
        System.out.println(
            "Not a good entry, type an integer between 0 and 2 corresponding to your choice!");
        listComputers();
        break;
    }
    sc.close();
  }

  private void listCompanies() {
    int choix = 0;
    Scanner sc = new Scanner(System.in);
    Query query = Query.builder().offset(iterCpn).limit(paginationCpn).build();
    try {
      for (Company cpn : cpnController.list(query)) {
        System.out.println(cpn.getName());
      }
    } catch (IndexOutOfBoundsException exn) {
      System.out.println("OutOfBounds, you need to make another choice");
      if (iterCpn < 0) {
        iterCpn += paginationCpn;
      } else {
        iterCpn -= paginationCpn;
      }
    }

    System.out.println("\n 1 Previous\n 2 Next\n 0 Back to menu\nYour choice ?");
    try {
      choix = Integer.valueOf(sc.nextLine());
    } catch (NumberFormatException exn) {
      System.out.println(
          "Not a good entry, type an integer between 0 and 2 corresponding to your choice!");
      menu();
    }

    switch (Integer.valueOf(choix)) {
      case 0:
        menu();
        break;
      case 1:
        iterCpn -= paginationCpn;
        listCompanies();
        break;
      case 2:
        iterCpn += paginationCpn;
        listCompanies();
        break;
      default:
        System.out.println(
            "Not a good entry, type an integer between 0 and 2 corresponding to your choice!");
        listCompanies();
        break;
    }
    sc.close();
  }

  private void showComputer() {
    Long choix = -1L;
    Scanner sc = new Scanner(System.in);
    System.out.println("Id of the computer you want to display, or -1 to go back to menu:");
    try {
      choix = Long.valueOf(sc.nextLine());
      if (choix == -1L) {
        menu();
      }
      System.out.println(cpuController.find(choix));
      showComputer();
    } catch (NumberFormatException exn) {
      System.out.println("Not a good entry, type a number of type Long!");
      showComputer();
    } finally {
      sc.close();
    }
  }

  private void createComputer() {
    LocalDate introduced = null;
    LocalDate discontinued = null;
    Long idCpn = null;
    Scanner sc = new Scanner(System.in);
    System.out.println("Computer Creation\nName of the computer, -1 to go back to menu:");
    String name = sc.nextLine();
    if (name.equals("-1")) {
      menu();
    } else if (name.equals("")) {
      System.out.println("Name can't be null!");
      createComputer();
    } else {
      System.out.println("Introduction date (format YYYY-MM-DD), -1 to go back to menu:");
      try {
        String resIntroduced = sc.nextLine();
        if (!resIntroduced.equals("")) {
          introduced = LocalDate.parse(sc.nextLine());
        }
      } catch (DateTimeParseException exn) {
        System.out.println("Not a good entry, type a date in format YYYY-MM-DD!");
        createComputer();
      }
      System.out.println("Discontinued date (format YYYY-MM-DD), -1 to go back to menu:");
      try {
        String resDiscontinued = sc.nextLine();
        if (!resDiscontinued.equals("")) {
          discontinued = LocalDate.parse(sc.nextLine());
        }
      } catch (DateTimeParseException exn) {
        System.out.println("Not a good entry, type a date in format YYYY-MM-DD!");
        createComputer();
      }
      if (!(introduced == null || discontinued == null)) {
        if (discontinued.isBefore(introduced)) {
          System.out.println("Discontinued date can't be before Introduced one... try again!");
          createComputer();
        }
      }
      System.out.println("Id of the company, -1 to go back to menu:");
      try {
        String resIdCpn = sc.nextLine();
        if (!resIdCpn.equals("")) {
          idCpn = Long.valueOf(resIdCpn);
          if (idCpn == -1L) {
            menu();
          }
        } else {
          resIdCpn = null;
        }
        ComputerDto cpuDto = ComputerDto.builder().build(); // to change
        cpuController.createComputer(ComputerDtoToDao.getInstance().map(cpuDto));
        createComputer();
      } catch (NumberFormatException exn) {
        System.out.println("Not a good entry, type a number of type Long!");
        createComputer();
      }
    }
    sc.close();
  }

  private void updateComputer() {
    LocalDate introduced = null;
    LocalDate discontinued = null;
    Long idCpn = null;
    Scanner sc = new Scanner(System.in);
    System.out.println("Computer Update\nId of the computer, -1 to go back to menu:");
    @SuppressWarnings("unused")
	Long idCpu = null;
    String resIdCpu = sc.nextLine();
    if (resIdCpu.equals("-1")) {
      menu();
    } else if (resIdCpu.equals("")) {
      System.out.println("Id can't be null!");
      updateComputer();
    } else {
      try {
        idCpu = Long.valueOf(resIdCpu);
      } catch (NumberFormatException exn) {
        System.out.println("Not a good entry, you need to type an id of type int!");
        updateComputer();
      }
    }
    System.out.println("\nName of the computer, -1 to go back to menu:");
    String name = sc.nextLine();
    if (name.equals("-1")) {
      menu();
    } else if (name.equals("")) {
      System.out.println("Name can't be null!");
      updateComputer();
    } else {
      System.out.println("Introduction date (format YYYY-MM-DD), -1 to go back to menu:");
      try {
        String resIntroduced = sc.nextLine();
        if (!resIntroduced.equals("")) {
          introduced = LocalDate.parse(sc.nextLine());
        }
      } catch (DateTimeParseException exn) {
        System.out.println("Not a good entry, type a date in format YYYY-MM-DD!");
        updateComputer();
      }
      System.out.println("Discontinued date (format YYYY-MM-DD), -1 to go back to menu:");
      try {
        String resDiscontinued = sc.nextLine();
        if (!resDiscontinued.equals("")) {
          discontinued = LocalDate.parse(sc.nextLine());
        }
      } catch (DateTimeParseException exn) {
        System.out.println("Not a good entry, type a date in format YYYY-MM-DD!");
        updateComputer();
      }
      if (!(introduced == null || discontinued == null)) {
        if (discontinued.isBefore(introduced)) {
          System.out.println("Discontinued date can't be before Introduced one... try again!");
          updateComputer();
        }
      }
      System.out.println("Id of the company, -1 to go back to menu:");
      try {
        String resIdCpn = sc.nextLine();
        if (!resIdCpn.equals("")) {
          idCpn = Long.valueOf(resIdCpn);
          if (idCpn == -1L) {
            menu();
          }
        } else {
          resIdCpn = null;
        }
        ComputerDto cpuDto = ComputerDto.builder().build(); // to change
        cpuController.updateComputer(ComputerDtoToDao.getInstance().map(cpuDto));
        updateComputer();
      } catch (NumberFormatException exn) {
        System.out.println("Not a good entry, type a number of type Long!");
        updateComputer();
      }
    }
    sc.close();
  }

  private void deleteComputer() {
    Long choix = -1L;
    Scanner sc = new Scanner(System.in);
    System.out.println("Id of the computer you want to delete, or -1 to go back to menu:");
    try {
      choix = Long.valueOf(sc.nextLine());
      if (choix == -1L) {
        menu();
      }
      cpuController.delete(choix);
      deleteComputer();
    } catch (NumberFormatException exn) {
      System.out.println("Not a good entry, type a number of type Long!");
      deleteComputer();
    } finally {
      sc.close();
    }
  }

  private void exitApplication() {
    System.exit(1);
  }

  public static void main(String[] args) {
    ComputerLineInterface cli = new ComputerLineInterface();
    cli.init();
  }
}
