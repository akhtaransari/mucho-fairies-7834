package com.eVotingHub.dto;

import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Election {
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private int id;
    private String name;
    private Date startDate;
    private Date endDate;
	
    
    public Election() {
    	super();
	}


	public Election(String name, Date startDate, Date endDate) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	@Override
	public String toString() {
		return "Election [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(endDate, id, name, startDate);
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
		return Objects.equals(endDate, other.endDate) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(startDate, other.startDate);
	}


	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
    
    
    
}
