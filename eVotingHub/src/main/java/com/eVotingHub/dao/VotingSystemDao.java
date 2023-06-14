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
	boolean registerVoter(Voter voter) 							throws AccessForbidden , InvalidCredentials  ,NoRecordFound, UnauthorizedAccess, UserMustBe18orAbove , SomeThingWentWrong;
    Voter loginVoter(String email, String password) 				throws AccessForbidden , InvalidCredentials ,MaximumLoginAttemptReached ,NoRecordFound, UnauthorizedAccess ,SomeThingWentWrong;
    List<Election> viewUpcomingElections() 								throws AccessForbidden  ,NoRecordFound, UnauthorizedAccess , SomeThingWentWrong;
    boolean castVote(Vote vote) throws AccessForbidden ,DuplicateEntry , NoRecordFound, UnauthorizedAccess , SomeThingWentWrong , WrongInput;
    List<Vote> viewVotingHistory(int voterId)							throws AccessForbidden ,DuplicateEntry , NoRecordFound, UnauthorizedAccess , SomeThingWentWrong , WrongInput;

    boolean createCandidate(Candidate candidate) 			throws AccessForbidden ,DuplicateEntry ,NoRecordFound, SomeThingWentWrong ,UserMustBe18orAbove , WrongInput;
    boolean updateCandidate(Candidate candidate) 			throws AccessForbidden ,DuplicateEntry ,NoRecordFound, SomeThingWentWrong ,UserMustBe18orAbove , WrongInput;
    boolean deleteCandidate(int candidateId) 				throws AccessForbidden ,DuplicateEntry ,InvalidCredentials ,MaximumLoginAttemptReached ,NoRecordFound, UnauthorizedAccess , SomeThingWentWrong ,UserMustBe18orAbove , WrongInput;

}
