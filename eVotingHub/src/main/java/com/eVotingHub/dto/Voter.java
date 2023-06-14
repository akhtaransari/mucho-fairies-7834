package com.eVotingHub.dto;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Voter {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    private String name;
    private String email;
    private String password;
    
    public Voter() {
		super();
	}

	public Voter(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Voter [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, name, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voter other = (Voter) obj;
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password);
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
    
}
