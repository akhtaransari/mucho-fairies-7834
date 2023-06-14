package com.eVotingHub.exceptions;

public class AccessForbidden extends Exception{

	private static final long serialVersionUID = 1L;
	public AccessForbidden(String message) {
		super(message);
	}
}
