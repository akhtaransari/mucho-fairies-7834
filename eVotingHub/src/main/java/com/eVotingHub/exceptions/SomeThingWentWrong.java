package com.eVotingHub.exceptions;

public class SomeThingWentWrong extends Exception{
	private static final long serialVersionUID = 1L;

	SomeThingWentWrong (String message){
		super(message);
	}
}
