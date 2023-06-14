package com.eVotingHub.app;

import java.util.Scanner;

import com.eVotingHub.utility.DBConnection;

import MainUI.UI;

public class App{
    public static void main( String[] args ){
    	DBConnection.getEM();
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("\u001B[32m"+"╔══════════════════════════════╗");
		System.out.println("║         eVotingHub           ║");
		System.out.println("╚══════════════════════════════╝"+"\u001B");
		UI.menu(scanner);	
		System.out.println("╔══════════════════════════════╗");
		System.out.println("║   	Thanking You	       ║");
		System.out.println("╚══════════════════════════════╝");
		
		scanner.close();
   
    }
}
