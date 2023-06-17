package com.eVotingHub.dto;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Voter {

	private String fisrtName;
    private String lastName;
    private int age;
    @Id
    private String email;
    private String password;
    
    public Voter() {
		super();
	}

	public Voter(String fisrtName, String lastName, int age, String email, String password) {
		super();
		this.fisrtName = fisrtName;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Voter [fisrtName=" + fisrtName + ", lastName=" + lastName + ", age=" + age + ", email="
				+ email + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, email, fisrtName, lastName, password);
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
		return age == other.age && Objects.equals(email, other.email) && Objects.equals(fisrtName, other.fisrtName)
				&& Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password);
	}

	

	public String getFisrtName() {
		return fisrtName;
	}

	public void setFisrtName(String fisrtName) {
		this.fisrtName = fisrtName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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
