package com.eVotingHub.exceptions;


public class InvalidCredentials extends Exception {
private static final long serialVersionUID = 1L;

	public InvalidCredentials(String message) {
		super(message);
	}
}
