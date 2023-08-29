package com.eVotingHub.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Voter {

	@Id
	private String email;
	private String firsttName;
    private String lastName;
    private int age;
    private String password;
    
    private boolean HasVoted;
    
    public Voter() {
		super();
	}

    
    public Voter(String email, String firsttName, String lastName, int age, String password) {
		super();
		this.email = email;
		this.firsttName = firsttName;
		this.lastName = lastName;
		this.age = age;
		this.password = password;
	}
    
	public Voter(String email, String firsttName, String lastName, int age, String password, boolean hasVoted) {
		super();
		this.email = email;
		this.firsttName = firsttName;
		this.lastName = lastName;
		this.age = age;
		this.password = password;
		HasVoted = hasVoted;
	}


	@Override
	public String toString() {
		return "Voter [email=" + email + ", firsttName=" + firsttName + ", lastName=" + lastName + ", age=" + age
				+ ", password=" + password + ", HasVoted=" + HasVoted + "]";
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirsttName() {
		return firsttName;
	}


	public void setFirsttName(String firsttName) {
		this.firsttName = firsttName;
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean getHasVoted() {
		return HasVoted;
	}


	public void setHasVoted(boolean hasVoted) {
		HasVoted = hasVoted;
	}

	


		    
}
