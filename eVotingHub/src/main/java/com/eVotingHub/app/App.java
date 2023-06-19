package com.eVotingHub.app;

import MainUI.UI;

public class App{
    public static void main( String[] args ){
    	
    	System.out.println("\u001B[32m"+"╔══════════════════════════════╗");
		System.out.println("║         eVotingHub           ║");
		System.out.println("╚══════════════════════════════╝"+"\u001B");
		UI.mainMenu();
		System.out.println("╔══════════════════════════════╗");
		System.out.println("║   	Thanking You	       ║");
		System.out.println("╚══════════════════════════════╝");
		
		UI.sc.close();
		
    }
}
