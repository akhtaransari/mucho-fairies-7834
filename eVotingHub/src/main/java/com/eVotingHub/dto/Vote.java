package com.eVotingHub.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vote {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    private int voterId;
    private int candidateId;
    private int electionId;
    
    public Vote() {
		super();
	}

	public Vote(int voterId, int candidateId, int electionId) {
		super();
		this.voterId = voterId;
		this.candidateId = candidateId;
		this.electionId = electionId;
	}

	@Override
	public String toString() {
		return "Vote [id=" + id + ", voterId=" + voterId + ", candidateId=" + candidateId + ", electionId=" + electionId
				+ "]";
	}

	public int getId() {
		return id;
	}

	public int getVoterId() {
		return voterId;
	}

	public void setVoterId(int voterId) {
		this.voterId = voterId;
	}

	public int getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	public int getElectionId() {
		return electionId;
	}

	public void setElectionId(int electionId) {
		this.electionId = electionId;
	}
    
    
}
