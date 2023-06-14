package com.eVotingHub.exceptions;

public class DuplicateEntry extends Exception{
	private static final long serialVersionUID = 1L;

	public DuplicateEntry(String message) {
        super(message);
    }
}
