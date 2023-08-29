package com.eVotingHub.dto;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Election{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int electionID;
    private String electionName;
    private LocalDate startDate;
    private LocalDate endDate;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Candidate> candidates = new ArrayList<>();
    
    public Election(int electionID, String electionName, LocalDate startDate, LocalDate endDate) {
    	super();
    	this.electionID = electionID;
    	this.electionName = electionName;
    	this.startDate = startDate;
    	this.endDate = endDate;
    }
	
	public Election() {
		super();
	}

	@Override
	public String toString() {
		return "Election [electionID=" + electionID + ", electionName=" + electionName + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", candidates=" + candidates + "]";
	}


	public Election(String electionName, LocalDate startDate, LocalDate endDate) {
		super();
		this.electionName = electionName;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Election(String electionName, LocalDate startDate, LocalDate endDate, List<Candidate> candidates) {
		super();
		this.electionName = electionName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.candidates = candidates;
	}

	public int getElectionID() {
		return electionID;
	}

	public void setElectionID(int electionID) {
		this.electionID = electionID;
	}

	public String getElectionName() {
		return electionName;
	}

	public void setElectionName(String electionName) {
		this.electionName = electionName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}
    

    
    
}
