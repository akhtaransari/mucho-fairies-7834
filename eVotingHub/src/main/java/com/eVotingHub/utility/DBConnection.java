package com.eVotingHub.utility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class DBConnection {
	
	public static EntityManager getEM() {
		return Persistence.createEntityManagerFactory("eVotingHub").createEntityManager();
	}
}
