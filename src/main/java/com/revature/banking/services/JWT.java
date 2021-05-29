package com.revature.banking.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

public class JWT {
	private static Key key;
	
	public static String getJws(String subject) {
		key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(PropertiesLoader.getSecretKey()));
		return Jwts.builder().setSubject(subject).signWith(key).compact();
	}
}
