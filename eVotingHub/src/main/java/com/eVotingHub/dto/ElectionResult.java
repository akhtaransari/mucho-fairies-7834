package com.eVotingHub.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ElectionResult {
	@Id
	private int electionId;
    private int candidateId;
    private int voteCount;
    
    public ElectionResult() {
		super();
	}

	public ElectionResult(int electionId, int candidateId, int voteCount) {
		super();
		this.electionId = electionId;
		this.candidateId = candidateId;
		this.voteCount = voteCount;
	}

	@Override
	public String toString() {
		return "ElectionResult [electionId=" + electionId + ", candidateId=" + candidateId + ", voteCount=" + voteCount
				+ "]";
	}

	public int getElectionId() {
		return electionId;
	}

	public void setElectionId(int electionId) {
		this.electionId = electionId;
	}

	public int getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
    
	
	
    
    
    
}
