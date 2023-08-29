package com.eVotingHub.dao;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	// register a voter 
	@Override
	public boolean registerVoter(Voter voter) throws AccessForbidden, InvalidCredentials, NoRecordFound,
	        UnauthorizedAccess, UserMustBe18orAbove, SomeThingWentWrong, DuplicateEntry {
		
	    EntityManager em = DBConnection.getEntityManager();
	    Voter find = null;
	    find = em.find(Voter.class, voter.getEmail());
	    if (find != null) {
	        em.close();
	        throw new DuplicateEntry("User Already Registered ");
	    }
	    try {
	        em.getTransaction().begin();

	        // Hash the password before storing it
	        String hashedPassword = DBConnection.hashPassword(voter.getPassword());
	        voter.setPassword(hashedPassword);

	        em.persist(voter);
	        em.getTransaction().commit();
	        em.close();
	        return true;
	    } catch (PersistenceException e) {
	        em.close();
	        throw new SomeThingWentWrong("Some Thing Went Wrong");
	    }
	}
	
	// login a voter using email id and password
	@Override
	public Voter loginVoter(String email, String password) throws AccessForbidden, InvalidCredentials,
	        MaximumLoginAttemptReached, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong {
	    EntityManager em = DBConnection.getEntityManager();
	    Voter find = null;
	    find = em.find(Voter.class, email);
	    if (find == null) {
	        em.close();
	        throw new NoRecordFound("No Such User Exists");
	    } else if (DBConnection.verifyPassword(password, find.getPassword())) {
	        em.close();
	        return find;
	    } else {
	        em.close();
	        throw new InvalidCredentials("Username or Password mismatch");
	    }
	}

	// this method will return history list of vote by email of user 
	@Override
	public List<Vote> viewVotingHistory(String emailID)
	        throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
	    EntityManager em = DBConnection.getEntityManager();
	    List<Vote> votes = null;
	    try {
	        String qlString = "SELECT c FROM Vote c";
			votes =  em.createQuery(qlString).getResultList();
	    } catch (PersistenceException e) {
	        e.printStackTrace();
	    } finally {
	        em.close();
	    }
	    
	    if (votes.isEmpty()) {
	        throw new NoRecordFound("No records found");
	    }
	    
	    return votes;
	}

	// this method is used to create a election  
	@Override
	public boolean addElection(Election election ,List<Candidate> list)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		EntityManager em = DBConnection.getEntityManager();
		
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

	// this method can delete any election bashed on election id
	@Override
	public boolean deleteElection(int id)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		EntityManager em = DBConnection.getEntityManager();
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

	// this method can modify update the current election with list of candidate
	@Override
	public boolean updateElection(Election election, List<Candidate> list)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		EntityManager em = DBConnection.getEntityManager();
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
	
	// this method return boolean if user has voted or not
	@Override
	public boolean voteConfirmation(String email, int electionID)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		EntityManager em = DBConnection.getEntityManager();
	    try {
	        Voter voter = em.find(Voter.class, email);
	        Election election = em.find(Election.class, electionID);

	        if (voter == null || election == null) {
	            em.close();
	            throw new NoRecordFound("Voter or Election not found");
	        }

	        

	        if (voter.getHasVoted()) {
	            em.close();
	            throw new AccessForbidden("You have already cast a vote in this election");
	        }

	        em.close();
	        return true;
	    } catch (PersistenceException e) {
	        em.close();
	        throw new SomeThingWentWrong("Something Went Wrong");
	    }
	}
	
	//this method return List of Available Election 
	@Override
	public List<Election> viewElections()
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		EntityManager em = DBConnection.getEntityManager();
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

	// this method will cast vote to a candidate  
	@Override
	public boolean castVote(Vote vote)
				throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
	
		 EntityManager em = DBConnection.getEntityManager();
		    try {
		        em.getTransaction().begin();

		        Voter voter = em.find(Voter.class, vote.getId());
		        Election election = em.find(Election.class, vote.getElectionId());

		        if (voter == null || election == null) {
		            em.close();
		            throw new NoRecordFound("Voter or Election not found");
		        }

		        if (voter.getHasVoted()) {
		            em.close();
		            throw new AccessForbidden("You have already cast a vote in this election");
		        }

		        // Update voter's voting status to indicate they have voted
		        voter.setHasVoted(true);

		        em.persist(vote);
		        em.getTransaction().commit();
		        em.close();
		        return true;
		    } catch (PersistenceException e) {
		        em.getTransaction().rollback();
		        em.close();
		        throw new SomeThingWentWrong("Something Went Wrong");
		    }
	}
	
	// this method return List of Candidates 
	@Override
	public List<Candidate> viewCandidates()
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		EntityManager em = DBConnection.getEntityManager();
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

	// Retrieves the results of a specific election.
	@Override
	public List<String> electionResult(int electionId)
			throws AccessForbidden, DuplicateEntry, NoRecordFound, UnauthorizedAccess, SomeThingWentWrong, WrongInput {
		// TODO Auto-generated method stub
		EntityManager em = DBConnection.getEntityManager();
	    try {
	        Election election = em.find(Election.class, electionId);

	        if (election == null) {
	            em.close();
	            throw new NoRecordFound("Election not found");
	        }

	        // Fetch all candidates associated with the election
	        List<Candidate> candidates = election.getCandidates();

	        if (candidates.isEmpty()) {
	            em.close();
	            throw new NoRecordFound("No candidates found for this election");
	        }

	        // Calculate the vote count for each candidate
	        Map<Candidate, Integer> candidateVoteCountMap = new HashMap<>();
	        for (Candidate candidate : candidates) {
	            int voteCount = em.createQuery("SELECT COUNT(v) FROM Vote v WHERE v.electionId = :electionId AND v.candidateId = :candidateId", Long.class)
	                    .setParameter("electionId", electionId)
	                    .setParameter("candidateId", candidate.getId())
	                    .getSingleResult().intValue();

	            candidateVoteCountMap.put(candidate, voteCount);
	        }

	        // Find the candidate with the maximum vote count
	        Candidate winner = Collections.max(candidateVoteCountMap.entrySet(),
	                Comparator.comparingInt(Map.Entry::getValue)).getKey();

	        em.close();

	        // Prepare the result message
	        String resultMessage = "The winner of this election is " + winner.getFirstName() + " " + winner.getLastName();
	        return Collections.singletonList(resultMessage);
	    } catch (PersistenceException e) {
	        em.close();
	        throw new SomeThingWentWrong("Something Went Wrong");
	    }
	}
}
