package MainUI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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

// Its a eVotingHub User Interface Layer
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
	// eVotingHub Services implementations  
	static OnlineVotingImplements onlineVotingImplements = new OnlineVotingImplements();
	public static Scanner sc = new Scanner(System.in);
	
	// Get integer input from the user
	public static int getIntInput() {
	        int input = 0;
	        boolean validInput = false;

	        while (!validInput) {
	            try {
	                System.out.print(CYAN+" Enter an "+RED+"integer"+RESET+" "+CYAN+"Type: "+RESET);
	                input = sc.nextInt();
	                validInput = true;
	            } catch (InputMismatchException e) {
	                System.out.println("Invalid input! Please enter an integer.");
	                sc.nextLine(); // Clear the input buffer
	            }
	        }
	        System.out.println();
	        return input;
	}
	
    // Get string input from the user
	public static String getStringInput() {
		System.out.print(CYAN+" Enter an "+PURPLE+"String"+RESET+" "+PURPLE+"Type: "+RESET);
		String next = sc.next();
		System.out.println();
		return next;

	}
    
	// Main menu for the application
	public static void mainMenu( ) {
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
			System.out.println("║ 6. View Candidates           ║");
			System.out.println("║ 0. Exit                      ║");
			System.out.println("╚══════════════════════════════╝" + RESET);
			
		choice = getIntInput();
		
			switch (choice) {
			case 1 -> adminUI();
			case 2 -> voterLoginUI();
			case 3 -> viewEletionsUI();
			case 4 -> viewEletionResultsUI();
			case 5 -> registerVoter();
			case 6 -> viewCandidates();
			default -> System.out.println();
			}
		} while (choice != 0);
		
        
	}
	
	// View elections from the user interface
	private static void viewEletionsUI() {
		try {
			onlineVotingImplements.viewElections().forEach(System.out::println);
		} catch (AccessForbidden | DuplicateEntry | NoRecordFound | UnauthorizedAccess | SomeThingWentWrong
				| WrongInput e) {
			System.out.println(e.getMessage());
		
		}
	}
	
	// Register a voter from the user/admin interface
	private static void registerVoter( ) {
		System.out.println("Enter firstName");
		String fisrtName = getStringInput();
		System.out.println("Enter lastName");
		String lasttName = getStringInput();
		System.out.println("Enter age");
		int age = getIntInput();
		System.out.println("Enter E-mail");
		String email = getStringInput();
		System.out.println("Enter password");
		String password = getStringInput();
		
		try {
			
			System.out.println(onlineVotingImplements.registerVoter(new Voter(email, lasttName, lasttName, age, password)) ? "Succesfull" : "not Succesfull");
		} catch (AccessForbidden | InvalidCredentials | NoRecordFound | UnauthorizedAccess | UserMustBe18orAbove
				| SomeThingWentWrong | DuplicateEntry e) {
			System.out.println(e.getMessage());
			
		}
			
		
	}
	
	// Voter login from the user interface
	private static void voterLoginUI( ) {
		System.out.println("Enter Email");
		String username = getStringInput();
		System.out.println("Enter Password");
		String password = getStringInput();
		try {
			voterMenu(onlineVotingImplements.loginVoter(username, password));
		} catch (AccessForbidden | InvalidCredentials | MaximumLoginAttemptReached | NoRecordFound | UnauthorizedAccess
				| SomeThingWentWrong e) {
			System.out.println(e.getMessage());
		}
	}
    
	// Voter menu after successful login
	private static void voterMenu(Voter voter) {
		int choice;
		do {
			System.out.println(YELLOW+"╔══════════════════════════════╗");
			System.out.println("║        Voter Menu            ║");
			System.out.println("╚══════════════════════════════╝");
			System.out.println("╔══════════════════════════════╗");
			System.out.println("║ 1. View Candidate            ║");
			System.out.println("║ 2. Cast Votes                ║");
			System.out.println("║ 3. Vote confirmation         ║");
			System.out.println("║ 4. View Voting History       ║");
			System.out.println("║ 0. Exit                      ║");
			System.out.println("╚══════════════════════════════╝" + RESET);
			
		choice = getIntInput();
		
			switch (choice) {
			case 1 -> viewCandidates();
			case 2 -> castVote(voter);
			case 3 -> voteConfirmationUI(voter);
			case 4 -> viewVotingHistoryUI(voter.getEmail());
			default -> System.out.println();
			}
		} while (choice != 0);
	}

	// View voting history for a voter
	private static void viewVotingHistoryUI(String email) {
		
		try {
			onlineVotingImplements.viewVotingHistory(email).forEach(i-> System.out.println(i));
		} catch (AccessForbidden | DuplicateEntry | NoRecordFound | UnauthorizedAccess | SomeThingWentWrong
				| WrongInput e) {
			System.out.println(e.getMessage());
		}
	}

	// Cast a vote for a candidate
	private static void castVote(Voter voter) {
		System.out.println("Enter CandidateID");
		int candidateID = getIntInput();
		System.out.println("Enter electionID");
		int electionid = getIntInput();
		Vote vote = new Vote(voter.getEmail(), candidateID, electionid);
		
		try {
			onlineVotingImplements.castVote(vote);
		} catch (AccessForbidden | DuplicateEntry | NoRecordFound | UnauthorizedAccess | SomeThingWentWrong
				| WrongInput e) {
			System.out.println(e.getMessage());
		}
		
	}

	// Confirm a vote for a voter
	private static void voteConfirmationUI(Voter voter) {

		System.out.println("Enter electionID");
		int electionid = getIntInput();
		try {
			System.out.println(onlineVotingImplements.voteConfirmation(voter.getEmail(),electionid) ? "Succesfull" : "Not Succesfull");
		} catch (AccessForbidden | DuplicateEntry | NoRecordFound | UnauthorizedAccess | SomeThingWentWrong
				| WrongInput e) {
			System.out.println(e.getMessage());
		}
		
		
	}

    // View election results
	private static void viewEletionResultsUI( ) {
		System.out.println("Enter Election ID");
		try {
			onlineVotingImplements.electionResult(sc.nextInt()).forEach(System.out::println);
		} catch (AccessForbidden | DuplicateEntry | NoRecordFound | UnauthorizedAccess | SomeThingWentWrong
				| WrongInput e) {
			System.out.println(e.getMessage());
		}
	}

    // Administrator login from the user interface
	private static void adminUI( ) {
		System.out.println(GREEN+"╔══════════════════════════════╗");
		System.out.println("║        Enter Username        ║");
		System.out.println("╚══════════════════════════════╝"+RESET);
		String usernameString = getStringInput();
		System.out.println(GREEN+"╔══════════════════════════════╗");
		System.out.println("║        Enter Password        ║");
		System.out.println("╚══════════════════════════════╝"+RESET);
		String	passwordString = getStringInput();
		try {
			if (onlineVotingImplements.loginAdministrator(usernameString, passwordString)) adminMenuUI();
		} catch (AccessForbidden | InvalidCredentials | MaximumLoginAttemptReached | NoRecordFound | UnauthorizedAccess
				| SomeThingWentWrong | WrongInput e) {
				System.out.println(e.getMessage());
		}
		
	}

    // Administrator menu after successful login
	private static void adminMenuUI( ) {
		int choice; 
		
		do {
			System.out.println(YELLOW+"╔══════════════════════════════╗");
			System.out.println("║          Admin Menu          ║");
			System.out.println("╚══════════════════════════════╝" + RESET);
			System.out.println(GREEN+"╔══════════════════════════════╗");
			System.out.println("║ 1. Create Elections          ║");
			System.out.println("║ 2. Update Elections          ║");
			System.out.println("║ 3. Delete Elections          ║");
			System.out.println("║ 0. Exit                      ║");
			System.out.println("╚══════════════════════════════╝" + RESET);

			choice = getIntInput();
			
			switch (choice) {
			case 1 -> createElectionUI();
			case 2 -> updateElectionUI();
			case 3 -> deleteElectioneUI();
			}
			
		} while (choice != 0);
				
	}

    // delete an election from the user interface
	private static void deleteElectioneUI( ) {
		System.out.println("Enter Election ID");
		int id = getIntInput();
		
		try {
			System.out.println(onlineVotingImplements.deleteElection(id) ? "Deleted " : "");
		} catch (AccessForbidden | DuplicateEntry | NoRecordFound | UnauthorizedAccess | SomeThingWentWrong
				| WrongInput e) {
		System.out.println(e.getMessage());
		}
		
	}

    // Update an election from the user interface
	private static void updateElectionUI( ) {
			System.out.println("Enter Election ID");
			int id = getIntInput();
			
			List<Candidate> idList = new ArrayList<>();
			System.out.println("Eneter How Many Candidates are Participating in Election");
			int candidates = getIntInput();
			for (int i = 1 ; i <= candidates;i++) {
				System.out.println("Enter Details for Candidate "+i);
				System.out.println("Enter First name");
				String firstName = getStringInput();
				System.out.println("Enter Last name");
			    String lastName = getStringInput();
			    System.out.println("Enter Profile");
			    sc.nextLine();
			    String profile = sc.nextLine();
			    System.out.println("Enter Agenda");
			    String agenda = sc.nextLine();
			    System.out.println("Candidate "+i+" Details Colletion Done");
			    idList.add(new Candidate(firstName, lastName, profile, agenda));
			}
			
			System.out.println("Candidate are Selected");
			System.out.println("Enter Election Name");
		    String electionNameString = sc.nextLine();
		    System.out.println("Enter Election Start Date");
		    LocalDate startDate = LocalDate.parse(getStringInput());
		    System.out.println("Enter Election End Date");
		    LocalDate endDate = LocalDate.parse(getStringInput());
		    
		    Election election = new Election(id,electionNameString, startDate, endDate);
		    
		    try {
				System.out.println(onlineVotingImplements.updateElection(election, idList) ? "Election Updated" : "");
			} catch (AccessForbidden | DuplicateEntry | NoRecordFound | UnauthorizedAccess | SomeThingWentWrong
					| WrongInput e) {
				System.out.println(e.getMessage());

			}	
	}

    // View candidates from the user interface
	private static void viewCandidates() {
		try {
			onlineVotingImplements.viewCandidates().forEach(System.out::println);
		} catch (AccessForbidden | DuplicateEntry | NoRecordFound | UnauthorizedAccess | SomeThingWentWrong
				| WrongInput e) {
			System.out.println(e.getMessage());
		}
	}
    
	// Create a new election from the user interface
	private static void createElectionUI() {
		List<Candidate> idList = new ArrayList<>();
		System.out.println("Eneter How Many Candidates are Participating in Election");
		int candidates = getIntInput();
		for (int i = 1 ; i <= candidates;i++) {
			System.out.println("Enter Details for Candidate "+i);
			System.out.println("Enter First name");
			String firstName = getStringInput();
			System.out.println("Enter Last name");
		    String lastName = getStringInput();
		    System.out.println("Enter Profile");
		    sc.nextLine();
		    String profile = sc.nextLine();
		    System.out.println("Enter Agenda");
		    String agenda = sc.nextLine();
		    System.out.println("Candidate "+i+" Details Colletion Done");
		    idList.add(new Candidate(firstName, lastName, profile, agenda));
		}
		
		System.out.println("Candidate are Selected");
		System.out.println("Enter Election Name");
	    String electionNameString = sc.nextLine();
	    System.out.println("Enter Election Start Date");
	    LocalDate startDate = LocalDate.parse(getStringInput());
	    System.out.println("Enter Election End Date");
	    LocalDate endDate = LocalDate.parse(getStringInput());
	    
	    Election election = new Election(electionNameString, startDate, endDate);
	    
	    try {
			System.out.println(onlineVotingImplements.addElection(election, idList) ? "Election Added" : "");
		} catch (AccessForbidden | DuplicateEntry | NoRecordFound | UnauthorizedAccess | SomeThingWentWrong
				| WrongInput e) {
			System.out.println(e.getMessage());

		}
	 
	    
	    
	}

}
