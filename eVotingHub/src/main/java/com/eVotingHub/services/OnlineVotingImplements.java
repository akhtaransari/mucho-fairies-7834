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

	@Override
	public boolean registerVoter(Voter voter) throws AccessForbidden, InvalidCredentials, NoRecordFound,
			UnauthorizedAccess, UserMustBe18orAbove, SomeThingWentWrong {
		// TODO Auto-generated method stub
		return votingSystemImplements.registerVoter(voter);
		
	}

	@Override
	public Voter loginVoter(String email, String password) throws AccessForbidden, InvalidCredentials,
			MaximumLoginAttemptReached, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong {
		return votingSystemImplements.loginVoter(email, password);
	}

	@Override
	public List<Election> viewUpcomingElections() throws AccessForbidden, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong {
		return votingSystemImplements.viewUpcomingElections();
	}

	@Override
	public boolean castVote(Vote vote)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		if (votingSystemImplements.castVote(vote) )return true;
		return false;
	}

	@Override
	public List<String> viewVotingHistory(int voterId)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {

		return null;
	}
	
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

	@Override
	public boolean createCandidateProfile(Candidate candidate)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, SomeThingWentWrong, UserMustBe18orAbove, WrongInput {
		if (votingSystemImplements.createCandidate(candidate)){
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public boolean updateCandidateProfile(Candidate candidate)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, SomeThingWentWrong, UserMustBe18orAbove, WrongInput {
		if (votingSystemImplements.updateCandidate(candidate)){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean deleteCandidateProfile(int candidateId) 
			throws AccessForbidden, DuplicateEntry, InvalidCredentials, MaximumLoginAttemptReached, NoRecordFound,
			UnauthorizedAccess, SomeThingWentWrong, UserMustBe18orAbove, WrongInput {
		if (votingSystemImplements.deleteCandidate(candidateId)){
			return true;
		}else {
			return false;
		}
	}
	
}
