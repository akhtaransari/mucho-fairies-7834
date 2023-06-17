package MainUI;

import java.time.LocalDate;
import java.util.Scanner;

import com.eVotingHub.dto.Candidate;
import com.eVotingHub.dto.Election;
import com.eVotingHub.dto.Vote;
import com.eVotingHub.dto.Voter;
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
	    static OnlineVotingImplements onlineVotingImplements = new OnlineVotingImplements();
		
	
	public static void mainMenu(Scanner sc) {
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
			System.out.println("║ 5. Voter Registration        ║");
			System.out.println("║ 0. Exit                      ║");
			System.out.println("╚══════════════════════════════╝" + RESET);
			
		choice = sc.nextInt();
		
			switch (choice) {
			case 1 -> adminUI(sc);
			case 2 -> voterLoginUI(sc);
			case 3 -> viewEletionResultsUI(sc);
			case 4 -> viewEletionResultsUI(sc);
			case 5 -> registerVoter(sc);
			default -> System.out.println();
			}
		} while (choice != 0);
		
        
	}
	

	private static void registerVoter(Scanner sc) {
		System.out.println("Enter firstName");
		String fisrtName = sc.next();
		System.out.println("Enter lastName");
		String lasttName = sc.next();
		System.out.println("Enter age");
		int age = sc.nextInt();
		System.out.println("Enter E-mail");
		String email = sc.next();
		System.out.println("Enter password");
		String password = sc.next();
		
		try {
			System.out.println(onlineVotingImplements.registerVoter(new Voter(fisrtName , lasttName,age, email, password)) ? "Succesfull" : "not Succesfull");
		} catch (AccessForbidden | InvalidCredentials | NoRecordFound | UnauthorizedAccess | UserMustBe18orAbove
				| SomeThingWentWrong | DuplicateEntry e) {
			System.out.println(e.getMessage());
			
		}
			
		
	}


	private static void voterLoginUI(Scanner sc) {
		System.out.println("Enter Username");
		String username = sc.next();
		System.out.println("Enter Password");
		String password = sc.next();
		try {
			voterMenu(sc,onlineVotingImplements.loginVoter(username, password));
		} catch (AccessForbidden | InvalidCredentials | MaximumLoginAttemptReached | NoRecordFound | UnauthorizedAccess
				| SomeThingWentWrong e) {
			System.out.println(e.getMessage());
		}
	}


	private static void voterMenu(Scanner sc , Voter voter) {
		int choice;
		do {
			System.out.println(YELLOW+"╔══════════════════════════════╗");
			System.out.println("║        Voter Menu            ║");
			System.out.println("╚══════════════════════════════╝");
			System.out.println("╔══════════════════════════════╗");
			System.out.println("║ 1. View Election Results     ║");
			System.out.println("║ 2. View Candidate            ║");
			System.out.println("║ 3. Cast Votes                ║");
			System.out.println("║ 4. Vote confirmation         ║");
			System.out.println("║ 5. View Voting History       ║");
			System.out.println("║ 0. Exit                      ║");
			System.out.println("╚══════════════════════════════╝" + RESET);
			
		choice = sc.nextInt();
		
			switch (choice) {
			case 1 -> viewEletionResultsUI(sc);
			case 2 -> createCandidateUI(sc);
			case 3 -> castVote(sc,voter);
			case 4 -> voteConfirmationUI(sc,voter);
			case 5 -> viewVotingHistoryUI(voter.getEmail());
			default -> System.out.println();
			}
		} while (choice != 0);
	}

	private static void viewVotingHistoryUI(String email) {
		
		try {
			onlineVotingImplements.viewVotingHistory(email).forEach(i-> System.out.println(i));
		} catch (AccessForbidden | DuplicateEntry | NoRecordFound | UnauthorizedAccess | SomeThingWentWrong
				| WrongInput e) {
			System.out.println(e.getMessage());
		}
	}


	private static void castVote(Scanner sc , Voter voter) {
		System.out.println("Enter CandidateID");
		int candidateID = sc.nextInt();
		System.out.println("Enter electionID");
		int electionid = sc.nextInt();
		Vote vote = new Vote(voter.getEmail(), candidateID, electionid);
		
		try {
			onlineVotingImplements.castVote(vote);
		} catch (AccessForbidden | DuplicateEntry | NoRecordFound | UnauthorizedAccess | SomeThingWentWrong
				| WrongInput e) {
			System.out.println(e.getMessage());
		}
		
	}


	private static void voteConfirmationUI(Scanner sc , Voter voter) {

		System.out.println("Enter electionID");
		int electionid = sc.nextInt();
		try {
			System.out.println(onlineVotingImplements.voteConfirmation(voter.getEmail(),electionid) ? "Succesfull" : "Not Succesfull");
		} catch (AccessForbidden | DuplicateEntry | NoRecordFound | UnauthorizedAccess | SomeThingWentWrong
				| WrongInput e) {
			System.out.println(e.getMessage());
		}
		
		
	}


	private static void viewEletionResultsUI(Scanner sc) {
		System.out.println("Enter Election ID");
		try {
			onlineVotingImplements.electionResult(sc.nextInt()).forEach(System.out::println);
		} catch (AccessForbidden | DuplicateEntry | NoRecordFound | UnauthorizedAccess | SomeThingWentWrong
				| WrongInput e) {
			System.out.println(e.getMessage());
		}
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
			System.out.println("║ 4. Create Elections          ║");
			System.out.println("║ 5. View Candidates           ║");
			System.out.println("║ 0. Exit                      ║");
			System.out.println("╚══════════════════════════════╝" + RESET);

			choice = sc.nextInt();
			
			switch (choice) {
			case 1 -> createCandidateUI(sc);
			case 2 -> updateCandidateUI(sc);
			case 3 -> deleteCandidateUI(sc);
			case 4 -> createElectionUI(sc);
			
			}
			
		} while (choice != 0);
				
	}


	private static void createElectionUI(Scanner sc) {
		
		System.out.println("Enter Candidtate ID");
		int candidateid = sc.nextInt();
		System.out.println("Enter Election Name");
		sc.nextLine();
	    String electionNameString = sc.nextLine();
	    System.out.println("Enter Election Start Date");
	    LocalDate startDate = LocalDate.parse(sc.next());
	    System.out.println("Enter Election End Date");
	    LocalDate endDate = LocalDate.parse(sc.next());
	    
	    try {
	    	System.out.println(onlineVotingImplements.addElection(new Election(candidateid,electionNameString, startDate, endDate)) ? "Election Created" : "");
		} catch (AccessForbidden | DuplicateEntry | NoRecordFound | UnauthorizedAccess | SomeThingWentWrong
				| WrongInput e) {
			System.out.println(e.getMessage());
		}
	}


	private static void updateCandidateUI(Scanner sc) {
		System.out.println("Enter ID");
		int id = sc.nextInt();
		System.out.println("Enter First Name");
		String fisrtName = sc.next();
		System.out.println("Enter Last Name");
	    String lastName = sc.next();
	    System.out.println("Enter Profile Descrption");
	    sc.nextLine();
	    String profile = sc.nextLine();
	    System.out.println("Enter Vvoting Agenda");
	    String agenda = sc.nextLine();
	  
	    Candidate candidate = new Candidate(id,fisrtName, lastName, profile, agenda);
	    try {
			if (onlineVotingImplements.updateCandidateProfile(candidate))System.out.println("Cadidate Updated");
		} catch (AccessForbidden | DuplicateEntry | NoRecordFound | SomeThingWentWrong | UserMustBe18orAbove
				| WrongInput e) {
			System.out.println(e.getMessage());
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
	    
	    Candidate candidate = new Candidate(fisrtName, lastName, profile, agenda);
	    try {
			if (onlineVotingImplements.createCandidateProfile(candidate))System.out.println("Cadidate Added");
		} catch (AccessForbidden | DuplicateEntry | NoRecordFound | SomeThingWentWrong | UserMustBe18orAbove
				| WrongInput e) {
			System.out.println(e.getMessage());
		}
	}


	private static void deleteCandidateUI(Scanner sc) {
		System.out.println("Enter Candidate ID");
		try {
			if (onlineVotingImplements.deleteCandidateProfile(sc.nextInt())) System.out.println("Cadidate Deleted");
		} catch (AccessForbidden | DuplicateEntry | InvalidCredentials | MaximumLoginAttemptReached | NoRecordFound
				| UnauthorizedAccess | SomeThingWentWrong | UserMustBe18orAbove | WrongInput e) {
			System.out.println(e.getMessage());
		}
	}
	
}
