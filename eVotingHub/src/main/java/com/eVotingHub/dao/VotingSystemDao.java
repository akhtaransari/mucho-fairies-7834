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

public interface VotingSystemDao {
	boolean registerVoter(Voter voter) 								throws AccessForbidden , InvalidCredentials  ,NoRecordFound, UnauthorizedAccess, UserMustBe18orAbove , SomeThingWentWrong , DuplicateEntry;
    Voter loginVoter(String email, String password) 				throws AccessForbidden , InvalidCredentials ,MaximumLoginAttemptReached ,NoRecordFound, UnauthorizedAccess ,SomeThingWentWrong;
    boolean castVote(Vote vote) 									throws AccessForbidden ,DuplicateEntry , NoRecordFound, UnauthorizedAccess , SomeThingWentWrong , WrongInput;
    List<Vote> viewVotingHistory(String emailID)						throws AccessForbidden ,DuplicateEntry , NoRecordFound, UnauthorizedAccess , SomeThingWentWrong , WrongInput;
    List<Election> viewElections() 								throws AccessForbidden ,DuplicateEntry , NoRecordFound, UnauthorizedAccess , SomeThingWentWrong , WrongInput;
    List<Candidate> viewCandidates() 								throws AccessForbidden ,DuplicateEntry , NoRecordFound, UnauthorizedAccess , SomeThingWentWrong , WrongInput;
    boolean addElection(Election election , List<Candidate> list)	throws AccessForbidden ,DuplicateEntry , NoRecordFound, UnauthorizedAccess , SomeThingWentWrong , WrongInput;
    boolean voteConfirmation(String email, int electionID) 			throws AccessForbidden ,DuplicateEntry , NoRecordFound, UnauthorizedAccess , SomeThingWentWrong , WrongInput;
    boolean deleteElection(int id)	throws AccessForbidden ,DuplicateEntry , NoRecordFound, UnauthorizedAccess , SomeThingWentWrong , WrongInput;
    boolean updateElection(Election election,List<Candidate> list)	throws AccessForbidden ,DuplicateEntry , NoRecordFound, UnauthorizedAccess , SomeThingWentWrong , WrongInput;
}
