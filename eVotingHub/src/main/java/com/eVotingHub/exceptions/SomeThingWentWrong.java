package com.eVotingHub.exceptions;

public class SomeThingWentWrong extends Exception{
	private static final long serialVersionUID = 1L;

	public SomeThingWentWrong (String message){
		super(message);
	}
}
