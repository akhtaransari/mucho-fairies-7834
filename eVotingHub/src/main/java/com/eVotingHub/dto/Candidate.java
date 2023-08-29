package com.eVotingHub.dto;

import 
java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Candidate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    private String firstName;
    private String lastName;
    private String profile;
    private String agenda;
    
    public Candidate() {
		super();
	}

	public Candidate(String firstName, String lastName, String profile, String agenda) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.profile = profile;
		this.agenda = agenda;
	}
	
	@Override
	public String toString() {
		return "Candidate [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", profile=" + profile
				+ ", agenda=" + agenda + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(agenda, firstName, id, lastName, profile);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candidate other = (Candidate) obj;
		return Objects.equals(agenda, other.agenda) && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(profile, other.profile);
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getAgenda() {
		return agenda;
	}

	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}
    
    
}
