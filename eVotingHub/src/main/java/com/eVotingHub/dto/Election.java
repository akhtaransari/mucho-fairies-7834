package com.eVotingHub.dto;


import java.time.LocalDate;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Election{
	
	@Id
	private int candidateid;
    private String electionName;
    private LocalDate startDate;
    private LocalDate endDate;
	
    public Election() {
    	super();
	}

    public Election(int candidateid, String electionName, LocalDate startDate, LocalDate endDate) {
		super();
		this.candidateid = candidateid;
		this.electionName = electionName;
		this.startDate = startDate;
		this.endDate = endDate;
	}





	@Override
	public String toString() {
		return "Election [id=" + candidateid + ", electionName=" + electionName + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(endDate, candidateid, electionName, startDate);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Election other = (Election) obj;
		return Objects.equals(endDate, other.endDate) && candidateid == other.candidateid && Objects.equals(electionName, other.electionName)
				&& Objects.equals(startDate, other.startDate);
	}


	public int getId() {
		return candidateid;
	}
	
	public String getelectionName() {
		return electionName;
	}


	public void setelectionName(String electionName) {
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
    
    
    
}
