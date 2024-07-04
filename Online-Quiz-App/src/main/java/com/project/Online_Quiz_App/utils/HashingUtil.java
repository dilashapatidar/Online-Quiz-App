package com.project.Online_Quiz_App.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class HashingUtil {
	private static final String SHA_256= "SHA-256";
	private final static SecureRandom RANDOM = new SecureRandom();
	
	 public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
	        MessageDigest digest = MessageDigest.getInstance(SHA_256);
	        digest.update(salt.getBytes());
	        byte[] hashedBytes = digest.digest(password.getBytes());
	        return Base64.getEncoder().encodeToString(hashedBytes);
	    }

	    public static String generateSalt() {
	        byte[] salt = new byte[16];
	        RANDOM.nextBytes(salt);
	        return Base64.getEncoder().encodeToString(salt);
	    }
}
