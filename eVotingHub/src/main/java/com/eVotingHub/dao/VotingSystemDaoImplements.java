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
	public List<Vote> viewVotingHistory(String emailID)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		EntityManager em = DBConnection.getEM();
		List<Vote>  votes = null;
		try {
			votes = em.createQuery("SELECT c FROM Vote c").getResultList();
			em.close();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		if (votes == null) {
			em.close();
			throw new NoRecordFound("No record Found");
		}
		return votes;
	}

	@Override
	public boolean addElection(Election election ,List<Candidate> list)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		EntityManager em = DBConnection.getEM();
		
		Election e = new Election();
		e.setElectionName(election.getElectionName());
		e.setStartDate(election.getStartDate());
		e.setEndDate(election.getEndDate());
		
		
		 for (int i = 0 ; i< list.size();i++) {
			 election.getCandidates().add(list.get(i));
		 }
		try {
			em.getTransaction().begin();
			em.persist(e);
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (PersistenceException es) {
			em.close();
			es.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteElection(int id)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		EntityManager em = DBConnection.getEM();
		Election election2 = null;
		election2 = em.find(Election.class,id);
		
		if (election2 == null) {
			em.close();
			throw new NoRecordFound("No Election Found");
		}
		try {
			em.getTransaction().begin();
			em.remove(election2);
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			em.close();
			throw new SomeThingWentWrong("Some Thing Went Wrong");
		}
	}

	@Override
	public boolean updateElection(Election election, List<Candidate> list)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		EntityManager em = DBConnection.getEM();
		Election election2 = null;
		election2 = em.find(Election.class,election.getElectionID());
		
		if (election2 == null) {
			em.close();
			throw new NoRecordFound("No Election Found");
		}
		try {
			em.getTransaction().begin();
			election2.setElectionName(election.getElectionName());
			election2.setStartDate(election.getStartDate());
			election2.setEndDate(election.getEndDate());
			 for (int i = 0 ; i< list.size();i++) {
				 election2.getCandidates().add(list.get(i));
			 }
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (PersistenceException es) {
			em.close();
			es.printStackTrace();
		}
		return false;		
	}
	
	//Pending
	@Override
	public boolean voteConfirmation(String email, int electionID)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		return false;
	}
	
	@Override
	public List<Election> viewElections()
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		EntityManager em = DBConnection.getEM();
		List<Election> list = null;
		try {
			list = em.createQuery("SELECT c FROM Election c").getResultList();
			if (list == null) {
				em.close();
				throw new NoRecordFound("No record Found");
			}
			em.close();
			return list;
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return list;
	}
	//Pending
	@Override
	public boolean castVote(Vote vote)
				throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
	
		return false;
	}

	@Override
	public List<Candidate> viewCandidates()
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		EntityManager em = DBConnection.getEM();
		List<Candidate> list = null;
		try {
			list = em.createQuery("SELECT c FROM Candidate c").getResultList();
			if (list == null) {
				em.close();
				throw new NoRecordFound("No record Found");
			}
			em.close();
			return list;
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return list;
	}
}
