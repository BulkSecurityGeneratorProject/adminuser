/**
 * 
 */
package com.rishi.adminuser.service.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author raushanr
 *
 */
public class PasswordGenerator {
	
	Logger log = LoggerFactory.getLogger(PasswordGenerator.class);

	public static String encode(String password) throws NoSuchAlgorithmException{
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
		return Base64.getEncoder().encodeToString(messageDigest.digest());
	}
	
}
