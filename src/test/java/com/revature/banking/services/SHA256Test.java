package com.revature.banking.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SHA256Test {

	@Test
	void testHash() {
		String message = "password";
		String expectedHash = "5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8";
		assertEquals(expectedHash, SHA256.hash(message));
	}

}
