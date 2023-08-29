package com.eVotingHub.services;

import java.util.List;

import com.eVotingHub.dao.VotingSystemDaoImplements;
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

public class OnlineVotingImplements implements OnlineVotingServices{
	VotingSystemDaoImplements votingSystemImplements = new VotingSystemDaoImplements();
	
	int adminLoginCounter = 0; 
	int voterLoginCounter = 0;
	
	// Registers a new voter in the system.
	@Override
	public boolean registerVoter(Voter voter) throws AccessForbidden, InvalidCredentials, NoRecordFound,
			UnauthorizedAccess, UserMustBe18orAbove, SomeThingWentWrong, DuplicateEntry {
		
		if (voter.getAge() < 17) {
			throw new UserMustBe18orAbove("User is Not Above 18");
		}
		return votingSystemImplements.registerVoter(voter);
		
	}
	// login method for voter 
	@Override
	public Voter loginVoter(String email, String password) throws AccessForbidden, InvalidCredentials,
			MaximumLoginAttemptReached, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong {
		voterLoginCounter++;
		Voter voter = votingSystemImplements.loginVoter(email,password);
		if (voterLoginCounter > 3) {
			throw new MaximumLoginAttemptReached("You have Atempted more then 3 time ");
		}else if (voter != null) {
			voterLoginCounter = 0;
		}else {
			throw new InvalidCredentials("Wrong Username and Password Provided");
		}
		return voter;
	}
	// cast a vote 
	@Override
	public boolean castVote(Vote vote)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		if (votingSystemImplements.castVote(vote) )return true;
		else throw new NoRecordFound("Record Not Found");
	}
	// Admin login method
	@Override
	public boolean loginAdministrator(String username, String password) throws AccessForbidden, InvalidCredentials,
			MaximumLoginAttemptReached, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		adminLoginCounter++;
		
		if (adminLoginCounter > 3) {
			throw new MaximumLoginAttemptReached("You have Atempted more then 3 time ");
		}else if (adminLoginCounter < 4 &&  username.equals("admin") && password.equals("admin")) {
			adminLoginCounter = 0;
			return true;
		}else {
			throw new InvalidCredentials("Wrong Username and Password Provided");
		}
		
	}
	
	// get voting confirmation based in email id for a election 
	@Override
	public boolean voteConfirmation(String email, int electionId)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		if (votingSystemImplements.voteConfirmation(email ,electionId)) return true;
		throw new SomeThingWentWrong("Something went Wrong");
	}
	
	// use of this method is to add a election along with candidates
	@Override
	public boolean addElection(Election election , List<Candidate> list)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		if (votingSystemImplements.addElection(election , list)) return true;
		throw new SomeThingWentWrong("Something went Wrong");
		
	}
	
	// user can view list of ongoing elections available to cast vote 
	@Override
	public List<Election> viewElections()
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		// TODO Auto-generated method stub
		return votingSystemImplements.viewElections();
	}

	// this method can delete election by their election id 
	@Override
	public boolean deleteElection(int id)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		if (votingSystemImplements.deleteElection(id)) return true;
		throw new SomeThingWentWrong("Something went Wrong");
	}

	// this method can update election by their election object and list of candidate   
	@Override
	public boolean updateElection(Election election, List<Candidate> list)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		if (votingSystemImplements.updateElection(election , list)) return true;
		throw new SomeThingWentWrong("Something went Wrong");
	}
	
	// voter can see his voting history by his email id
	@Override
	public List<Vote> viewVotingHistory(String emailID)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		return votingSystemImplements.viewVotingHistory(emailID); 	
	}
	
	// user can see the election result by election id
	@Override
	public List<String> electionResult(int electionId)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		// TODO Auto-generated method stub
		return votingSystemImplements.electionResult(electionId);
	}
	
	// user can view list of available candidates
	@Override
	public List<Candidate> viewCandidates()
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		return votingSystemImplements.viewCandidates();
	}
}
