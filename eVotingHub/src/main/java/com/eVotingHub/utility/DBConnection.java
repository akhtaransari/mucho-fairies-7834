package com.eVotingHub.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class DBConnection {
	// Returns an instance of EntityManager that can be used to interact with the database.
	public static EntityManager getEntityManager() {

		return Persistence.createEntityManagerFactory("eVotingHub").createEntityManager();
	}
	
	// Password Hash Implementation 
	public static String hashPassword(String password) {
	    try {
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        byte[] hashBytes = md.digest(password.getBytes());

	        // Convert the hash to a hex string
	        try (Formatter formatter = new Formatter()) {
	            for (byte b : hashBytes) {
	                formatter.format("%02x", b);
	            }
	            return formatter.toString();
	        }
	    } catch (NoSuchAlgorithmException e) {
	        throw new RuntimeException("Hashing algorithm not available", e);
	    }
	}
	
    // Compare the hashed password with the stored hash to Verify Login
	public static boolean verifyPassword(String password, String storedHash) {
	    try {
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        byte[] hashBytes = md.digest(password.getBytes());

	        // Convert the hash to a hex string
	        try (Formatter formatter = new Formatter()) {
	            for (byte b : hashBytes) {
	                formatter.format("%02x", b);
	            }
	            String hashedPassword = formatter.toString();

	            // Compare the hashed password with the stored hash
	            return hashedPassword.equals(storedHash);
	        }
	    } catch (NoSuchAlgorithmException e) {
	        throw new RuntimeException("Hashing algorithm not available", e);
	    }
	}
	
}
