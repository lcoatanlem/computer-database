package com.excilys.computerdatabase.ui;

import java.util.Scanner;

import com.excilys.computerdatabase.service.CompanyController;
import com.excilys.computerdatabase.service.ComputerController;

public class ComputerLineInterface{
	private int iterCpu;
	private final int paginationCpu = 50;
	private int iterCpn;
	private final int paginationCpn = 10;
	private ComputerController cpuController;
	private CompanyController cpnController;

	public ComputerLineInterface(){
		cpuController = new ComputerController();
		cpnController = new CompanyController();
		iterCpu = 0;
		iterCpn = 0;
	}

	private void init(){
		menu();
	}
	private void menu(){
		Scanner sc = new Scanner(System.in);
		System.out.println("\tMenu\n\n 1 List companies\n 2 List computers\n 3 Show computer details\n"
				+ " 4 Create a computer\n 5 Update a computer\n 6 Delete a computer\n"
				+ "\nVotre choix ?");
		String strChoix = sc.nextLine();
		while (!(strChoix.equals("1") || strChoix.equals("2") || strChoix.equals("3") || strChoix.equals("4")
				|| strChoix.equals("5")	|| strChoix.equals("6") || strChoix.equals("7"))){
			System.out.println("Not a good entry, type an integer between 1 and 6 corresponding to your choice!");
			strChoix = sc.nextLine();
		}
		int choix = Integer.valueOf(strChoix);

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
		case 7: 
			exitApplication();
			break;
		default:
			break;
		}
		sc.close();
	}
	private void listComputers(){
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
		String strChoix = sc.nextLine();
		while (!(strChoix.equals("0") || strChoix.equals("1") || strChoix.equals("2"))){
			System.out.println("Not a good entry, type an integer between 0 and 2 corresponding to your choice!");
			strChoix = sc.nextLine();
		}
		int choix = Integer.valueOf(strChoix);

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
			break;
		}
		sc.close();
	}
	private void listCompanies(){
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
		String strChoix = sc.nextLine();
		while (!(strChoix.equals("0") || strChoix.equals("1") || strChoix.equals("2"))){
			System.out.println("Not a good entry, type an integer between 0 and 2 corresponding to your choice!");
			strChoix = sc.nextLine();
		}
		int choix = Integer.valueOf(strChoix);

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
			break;
		}
		sc.close();
	}
	private void showComputer(){
	}
	private void createComputer(){
	}
	private void updateComputer(){
	}
	private void deleteComputer(){
	}
	private void exitApplication(){
	}

	public static void main(String args[]){
		ComputerLineInterface cli = new ComputerLineInterface();
		cli.init();
	}
}
