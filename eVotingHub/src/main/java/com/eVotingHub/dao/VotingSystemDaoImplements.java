package com.eVotingHub.dao;

import java.util.List;

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
import com.eVotingHub.utility.DBConnection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

public class VotingSystemDaoImplements  implements VotingSystemDao{

	@Override
	public boolean registerVoter(Voter voter) throws AccessForbidden, InvalidCredentials, NoRecordFound,
			UnauthorizedAccess, UserMustBe18orAbove, SomeThingWentWrong, DuplicateEntry {
		EntityManager em = DBConnection.getEM();
		Voter find = null;
		find = em.find(Voter.class, voter.getEmail());
		if (find != null) {
			em.close();
			throw new DuplicateEntry("User Already Registered ");
		}
		try {
			em.getTransaction().begin();
			em.persist(voter);
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (PersistenceException e) {
			em.close();
			throw new SomeThingWentWrong("Some Thing Went Wrong");
		}
		
	}

	@Override
	public Voter loginVoter(String email, String password) throws AccessForbidden, InvalidCredentials,
			MaximumLoginAttemptReached, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong {
		EntityManager em = DBConnection.getEM();
		Voter find = null;
		find = em.find(Voter.class, email);
		if (find == null) {
			em.close();
			throw new NoRecordFound("No Such User Exists");
		}else if (find.getEmail().equals(email) && find.getPassword().equals(password)) {
			return find;
		}
		throw new InvalidCredentials("Username or Password mismatch");
	}

	@Override
	public List<Election> viewUpcomingElections() throws AccessForbidden, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong {
		return null;
	}

	@Override
	public boolean castVote(Vote vote)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Vote> viewVotingHistory(int voterId)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public boolean createCandidate(Candidate candidate)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, SomeThingWentWrong, UserMustBe18orAbove, WrongInput {
		// TODO Auto-generated method stub
		
		EntityManager em = DBConnection.getEM();
		try {
			em.getTransaction().begin();
			em.persist(candidate);
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (PersistenceException e) {
			em.close();
			throw new SomeThingWentWrong("Some Thing Went Wrong");
		}
		
		
	}

	@Override
	public boolean updateCandidate(Candidate candidate)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, SomeThingWentWrong, UserMustBe18orAbove, WrongInput {
		EntityManager em = DBConnection.getEM();
		Candidate find = em.find(Candidate.class,candidate.getId());
		
		if (find == null) {
			em.close();
			throw new NoRecordFound("No Candidate found");
		}
		
		try {
			em.getTransaction().begin();
			find.setAgenda(candidate.getAgenda());
			find.setFirstName(candidate.getFirstName());
			find.setLastName(candidate.getLastName());
			find.setProfile(candidate.getProfile());
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (PersistenceException e) {
			em.close();
			throw new SomeThingWentWrong("Some Thing Went Wrong");
		}
	}

	@Override
	public boolean deleteCandidate(int candidateId)
			throws AccessForbidden, DuplicateEntry, InvalidCredentials, MaximumLoginAttemptReached, NoRecordFound,
			UnauthorizedAccess, SomeThingWentWrong, UserMustBe18orAbove, WrongInput {
		EntityManager em = DBConnection.getEM();
		Candidate find = em.find(Candidate.class,candidateId);
		if (find == null) {
			em.close();
			throw new NoRecordFound("No Candidate found");
		}
		
		try {
			em.getTransaction().begin();
			em.remove(find);
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (PersistenceException e) {
			em.close();
			throw new SomeThingWentWrong("Some Thing Went Wrong");
		}
		
	}

	@Override
	public boolean voteConfirmation(String email, int electionID)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		return false;
	}

	@Override
	public boolean addElection(Election election)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		EntityManager em = DBConnection.getEM();
		Election find = em.find(Election.class,election.getId());
		if (find != null) {
			em.close();
			throw new NoRecordFound("Election Already Exists");
		}
		
		try {
			em.getTransaction().begin();
			em.persist(election);
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (PersistenceException e) {
			em.close();
			throw new SomeThingWentWrong("Some Thing Went Wrong");
		}
	}

}
