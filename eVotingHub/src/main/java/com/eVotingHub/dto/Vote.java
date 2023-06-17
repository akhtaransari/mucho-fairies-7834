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
    private String voterEmail;
    private int candidateId;
    private int electionId;
    
    public Vote() {
		super();
	}

	public Vote(String voterEmail, int candidateId, int electionId) {
		super();
		this.voterEmail = voterEmail;
		this.candidateId = candidateId;
		this.electionId = electionId;
	}

	@Override
	public String toString() {
		return "Vote [id=" + id + ", voterId=" + voterEmail + ", candidateId=" + candidateId + ", electionId=" + electionId
				+ "]";
	}

	public int getId() {
		return id;
	}

	public String getVoterId() {
		return voterEmail;
	}

	public void setVoterId(String voterEmail) {
		this.voterEmail = voterEmail;
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
