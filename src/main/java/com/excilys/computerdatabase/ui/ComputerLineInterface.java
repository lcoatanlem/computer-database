package com.excilys.computerdatabase.ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.excilys.computerdatabase.exception.NotSuchCompanyException;
import com.excilys.computerdatabase.exception.NotSuchComputerException;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerController;

public class ComputerLineInterface{
	private int iterCpu;
	private final int paginationCpu = 50;
	private Long iterCpn;
	private final int paginationCpn = 10;
	private ComputerController cpuController;
	private CompanyService cpnController;

	public ComputerLineInterface(){
		cpuController = new ComputerController();
		cpnController = new CompanyService();
		iterCpu = 0;
		iterCpn = 0L;
	}

	private void init(){
		menu();
	}
	private void menu(){
		Scanner sc = new Scanner(System.in);
		int choix = 0;
		System.out.println("\tMenu\n\n 1 List companies\n 2 List computers\n 3 Show computer details\n"
				+ " 4 Create a computer\n 5 Update a computer\n 6 Delete a computer\n -1 Exit application\n"
				+ "\nVotre choix ?");
		try{
			choix = Integer.valueOf(sc.nextLine());
		} catch (NumberFormatException e){
			System.out.println("Not a good entry, type an integer between 1 and 6 corresponding to your choice, -1 to exit!");
			menu();
		}
		switch(Integer.valueOf(choix)){
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
			System.out.println("Not a good entry, type an integer between 1 and 6 corresponding to your choice, -1 to exit!");
			menu();
			break;
		}
		sc.close();
	}
	private void listComputers(){
		int choix = 0;
		Scanner sc = new Scanner(System.in);
		try{
			System.out.println(cpuController.listComputers(iterCpu, paginationCpu));
		} catch (IndexOutOfBoundsException e){
			System.out.println("OutOfBounds, you need to make another choice");
			if(iterCpu<0){
				iterCpu += paginationCpu;
			} else {
				iterCpu -= paginationCpu;
			}
		}

		System.out.println("\n 1 Previous\n 2 Next\n 0 Back to menu\nYour choice ?");
		try{
			choix = Integer.valueOf(sc.nextLine());
		} catch (NumberFormatException e){
			System.out.println("Not a good entry, type an integer between 0 and 2 corresponding to your choice!");
			menu();
		}
		switch(Integer.valueOf(choix)){
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
			System.out.println("Not a good entry, type an integer between 0 and 2 corresponding to your choice!");
			listComputers();
			break;
		}
		sc.close();
	}
	private void listCompanies(){
		int choix = 0;
		Scanner sc = new Scanner(System.in);

		try{
			System.out.println(cpnController.listCompanies(iterCpn, paginationCpn));
		} catch (IndexOutOfBoundsException e){
			System.out.println("OutOfBounds, you need to make another choice");
			if(iterCpn<0){
				iterCpn += paginationCpn;
			} else {
				iterCpn -= paginationCpn;
			}
		}

		System.out.println("\n 1 Previous\n 2 Next\n 0 Back to menu\nYour choice ?");
		try{
			choix = Integer.valueOf(sc.nextLine());
		} catch (NumberFormatException e){
			System.out.println("Not a good entry, type an integer between 0 and 2 corresponding to your choice!");
			menu();
		}

		switch(Integer.valueOf(choix)){
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
			System.out.println("Not a good entry, type an integer between 0 and 2 corresponding to your choice!");
			listCompanies();
			break;
		}
		sc.close();
	}
	private void showComputer(){
		Long choix = -1L;
		Scanner sc = new Scanner(System.in);
		System.out.println("Id of the computer you want to display, or -1 to go back to menu:");
		try{
			choix = Long.valueOf(sc.nextLine());
			if (choix == -1L){
				menu();
			}
			System.out.println(cpuController.showComputer(choix));
			showComputer();
		} catch (NumberFormatException e){
			System.out.println("Not a good entry, type a number of type Long!");
			showComputer();
		} catch (NotSuchComputerException e) {
			System.out.println("This computer does not exists, try again!");
			showComputer();
		} finally {
			sc.close();
		}
	}
	private void createComputer(){
		LocalDate introduced = null;
		LocalDate discontinued = null;
		Long idCpn = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Computer Creation\nName of the computer, -1 to go back to menu:");
		String name = sc.nextLine();
		if (name.equals("-1")){
			menu();
		} else if (name.equals("")){
			System.out.println("Name can't be null!");
			createComputer();
		} else {
			System.out.println("Introduction date (format YYYY-MM-DD), -1 to go back to menu:");
			try{
				String resIntroduced = sc.nextLine();
				if (!resIntroduced.equals("")){
					introduced = LocalDate.parse(sc.nextLine());
				}
			} catch (DateTimeParseException e){
				System.out.println("Not a good entry, type a date in format YYYY-MM-DD!");
				createComputer();
			}
			System.out.println("Discontinued date (format YYYY-MM-DD), -1 to go back to menu:");
			try{
				String resDiscontinued = sc.nextLine();
				if (!resDiscontinued.equals("")){
					discontinued = LocalDate.parse(sc.nextLine());
				}
			} catch (DateTimeParseException e){
				System.out.println("Not a good entry, type a date in format YYYY-MM-DD!");
				createComputer();
			}
			if (!(introduced == null || discontinued == null)){
				if (discontinued.isBefore(introduced)){
					System.out.println("Discontinued date can't be before Introduced one... try again!");
					createComputer();
				}
			}
			System.out.println("Id of the company, -1 to go back to menu:");
			try{
				String resIdCpn = sc.nextLine();
				if (!resIdCpn.equals("")){
					idCpn = Long.valueOf(resIdCpn);
					if (idCpn == -1L){
						menu();
					}
				} else {
					resIdCpn = null;
				}
				cpuController.createComputer(name, introduced, discontinued, idCpn);
				createComputer();
			} catch (NumberFormatException e){
				System.out.println("Not a good entry, type a number of type Long!");
				createComputer();
			} catch (NotSuchCompanyException e) {
				System.out.println("This company does not exists, try again!");
				createComputer();
			}
		}
		sc.close();
	}
	private void updateComputer(){
		LocalDate introduced = null;
		LocalDate discontinued = null;
		Long idCpn = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Computer Update\nId of the computer, -1 to go back to menu:");
		Long idCpu = null;
		String resIdCpu = sc.nextLine();
		if (resIdCpu.equals("-1")){
			menu();
		} else if (resIdCpu.equals("")){
			System.out.println("Id can't be null!");
			updateComputer();
		} else {
			try{
				idCpu = Long.valueOf(resIdCpu);
			} catch (NumberFormatException e){
				System.out.println("Not a good entry, you need to type an id of type int!");
				updateComputer();
			}
		}
		System.out.println("\nName of the computer, -1 to go back to menu:");
		String name = sc.nextLine();
		if (name.equals("-1")){
			menu();
		} else if (name.equals("")){
			System.out.println("Name can't be null!");
			updateComputer();
		} else {
			System.out.println("Introduction date (format YYYY-MM-DD), -1 to go back to menu:");
			try{
				String resIntroduced = sc.nextLine();
				if (!resIntroduced.equals("")){
					introduced = LocalDate.parse(sc.nextLine());
				}
			} catch (DateTimeParseException e){
				System.out.println("Not a good entry, type a date in format YYYY-MM-DD!");
				updateComputer();
			}
			System.out.println("Discontinued date (format YYYY-MM-DD), -1 to go back to menu:");
			try{
				String resDiscontinued = sc.nextLine();
				if (!resDiscontinued.equals("")){
					discontinued = LocalDate.parse(sc.nextLine());
				}
			} catch (DateTimeParseException e){
				System.out.println("Not a good entry, type a date in format YYYY-MM-DD!");
				updateComputer();
			}
			if (!(introduced == null || discontinued == null)){
				if (discontinued.isBefore(introduced)){
					System.out.println("Discontinued date can't be before Introduced one... try again!");
					updateComputer();
				}
			}
			System.out.println("Id of the company, -1 to go back to menu:");
			try{
				String resIdCpn = sc.nextLine();
				if (!resIdCpn.equals("")){
					idCpn = Long.valueOf(resIdCpn);
					if (idCpn == -1L){
						menu();
					}
				} else {
					resIdCpn = null;
				}
				cpuController.updateComputer(idCpu, name, introduced, discontinued, idCpn);
				updateComputer();
			} catch (NumberFormatException e){
				System.out.println("Not a good entry, type a number of type Long!");
				updateComputer();
			} catch (NotSuchCompanyException e) {
				System.out.println("This company does not exists, try again!");
				updateComputer();
			} catch (NotSuchComputerException e){
				System.out.println("This id doesn't exists, try again!");
				updateComputer();
			}
		}
		sc.close();
	}
	private void deleteComputer(){
		Long choix = -1L;
		Scanner sc = new Scanner(System.in);
		System.out.println("Id of the computer you want to delete, or -1 to go back to menu:");
		try{
			choix = Long.valueOf(sc.nextLine());
			if (choix == -1L){
				menu();
			}
			cpuController.deleteComputer(choix);
			deleteComputer();
		} catch (NumberFormatException e){
			System.out.println("Not a good entry, type a number of type Long!");
			deleteComputer();
		} catch (NotSuchComputerException e) {
			System.out.println("This computer does not exists, try again!");
			deleteComputer();
		} finally {
			sc.close();
		}
	}
	private void exitApplication(){
		System.exit(1);
	}

	public static void main(String args[]){
		ComputerLineInterface cli = new ComputerLineInterface();
		cli.init();
	}
}
