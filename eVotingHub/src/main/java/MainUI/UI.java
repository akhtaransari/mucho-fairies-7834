package MainUI;

import java.util.Scanner;

import com.eVotingHub.dto.Candidate;
import com.eVotingHub.exceptions.AccessForbidden;
import com.eVotingHub.exceptions.DuplicateEntry;
import com.eVotingHub.exceptions.InvalidCredentials;
import com.eVotingHub.exceptions.MaximumLoginAttemptReached;
import com.eVotingHub.exceptions.NoRecordFound;
import com.eVotingHub.exceptions.SomeThingWentWrong;
import com.eVotingHub.exceptions.UnauthorizedAccess;
import com.eVotingHub.exceptions.UserMustBe18orAbove;
import com.eVotingHub.exceptions.WrongInput;
import com.eVotingHub.services.OnlineVotingImplements;

public class UI {

	    // ANSI escape codes for text colors
	    public static final String RESET = "\u001B[0m";
	    public static final String BLACK = "\u001B[30m";
	    public static final String RED = "\u001B[31m";
	    public static final String GREEN = "\u001B[32m";
	    public static final String YELLOW = "\u001B[33m";
	    public static final String BLUE = "\u001B[34m";
	    public static final String PURPLE = "\u001B[35m";
	    public static final String CYAN = "\u001B[36m";
	    public static final String WHITE = "\u001B[37m";
		
	
	public static void menu(Scanner sc) {
		int choice;
		
		do {
			System.out.println(YELLOW+"╔══════════════════════════════╗");
			System.out.println("║        Main Menu             ║");
			System.out.println("╚══════════════════════════════╝");
			System.out.println("╔══════════════════════════════╗");
			System.out.println("║ 1. Admin Login               ║");
			System.out.println("║ 2. Voter Login               ║");
			System.out.println("║ 3. View Elections            ║");
			System.out.println("║ 4. View Election Results     ║");
			System.out.println("║ 0. Exit                      ║");
			System.out.println("╚══════════════════════════════╝" + RESET);
			
		choice = sc.nextInt();
		
			switch (choice) {
			case 1 -> adminUI(sc);
			case 2 -> voterUI(sc);
			case 3 -> viewEletionResultsUI(sc);
			case 4 -> viewEletionResultsUI(sc);
			default -> System.out.println();
			}
		} while (choice != 0);
		
        
	}
	

	private static void voterUI(Scanner sc) {
		
	}

	private static void viewEletionResultsUI(Scanner sc) {
		
	}

	private static void adminUI(Scanner sc) {
		System.out.println(GREEN+"╔══════════════════════════════╗");
		System.out.println("║        Enter Username        ║");
		System.out.println("╚══════════════════════════════╝"+RESET);
		String usernameString = sc.next();
		System.out.println(GREEN+"╔══════════════════════════════╗");
		System.out.println("║        Enter Password        ║");
		System.out.println("╚══════════════════════════════╝"+RESET);
		String	passwordString = sc.next();
		OnlineVotingImplements onlineVotingImplements = new OnlineVotingImplements();
		try {
			if (onlineVotingImplements.loginAdministrator(usernameString, passwordString)) adminMenuUI(sc);
		} catch (AccessForbidden | InvalidCredentials | MaximumLoginAttemptReached | NoRecordFound | UnauthorizedAccess
				| SomeThingWentWrong | WrongInput e) {
				System.out.println(e.getMessage());
		}
		
	}


	private static void adminMenuUI(Scanner sc) {
		int choice; 
		
		do {
			System.out.println(YELLOW+"╔══════════════════════════════╗");
			System.out.println("║          Admin Menu          ║");
			System.out.println("╚══════════════════════════════╝" + RESET);
			System.out.println(GREEN+"╔══════════════════════════════╗");
			System.out.println("║ 1. Create Candidate          ║");
			System.out.println("║ 2. Update Candidate          ║");
			System.out.println("║ 3. Delete Candidate          ║");
			System.out.println("║ 0. Exit                      ║");
			System.out.println("╚══════════════════════════════╝" + RESET);

			choice = sc.nextInt();
			
			switch (choice) {
			case 1 -> createCandidateUI(sc);
			case 2 -> updateCandidateUI(sc);
			case 3 -> deleteCandidateUI(sc);
			}
			
		} while (choice != 0);
				
	}


	private static void updateCandidateUI(Scanner sc) {
		System.out.println("Enter First Name");
		String fisrtName = sc.next();
		System.out.println("Enter Last Name");
	    String lastName = sc.next();
	    System.out.println("Enter Profile Descrption");
	    sc.nextLine();
	    String profile = sc.nextLine();
	    System.out.println("Enter Vvoting Agenda");
	    String agenda = sc.nextLine();
	    
	    OnlineVotingImplements onlineVotingImplements = new OnlineVotingImplements();
	    Candidate candidate = new Candidate(fisrtName, lastName, profile, agenda);
	    try {
			if (onlineVotingImplements.updateCandidateProfile(candidate))System.out.println("Cadidate Updated");
		} catch (AccessForbidden | DuplicateEntry | NoRecordFound | SomeThingWentWrong | UserMustBe18orAbove
				| WrongInput e) {
			e.printStackTrace();
		}
	}


	private static void createCandidateUI(Scanner sc) {
		System.out.println("Enter First Name");
		String fisrtName = sc.next();
		System.out.println("Enter Last Name");
	    String lastName = sc.next();
	    System.out.println("Enter Profile Descrption");
	    sc.nextLine();
	    String profile = sc.nextLine();
	    System.out.println("Enter Vvoting Agenda");
	    String agenda = sc.nextLine();
	    
	    OnlineVotingImplements onlineVotingImplements = new OnlineVotingImplements();
	    Candidate candidate = new Candidate(fisrtName, lastName, profile, agenda);
	    try {
			if (onlineVotingImplements.createCandidateProfile(candidate))System.out.println("Cadidate Added");
		} catch (AccessForbidden | DuplicateEntry | NoRecordFound | SomeThingWentWrong | UserMustBe18orAbove
				| WrongInput e) {
			e.printStackTrace();
		}
	}


	private static void deleteCandidateUI(Scanner sc) {
		System.out.println("Enter Candidate ID");
		OnlineVotingImplements onlineVotingImplements = new OnlineVotingImplements();
		try {
			if (onlineVotingImplements.deleteCandidateProfile(sc.nextInt())) System.out.println("Cadidate Deleted");
		} catch (AccessForbidden | DuplicateEntry | InvalidCredentials | MaximumLoginAttemptReached | NoRecordFound
				| UnauthorizedAccess | SomeThingWentWrong | UserMustBe18orAbove | WrongInput e) {
			e.printStackTrace();
		}
	}
	
}
