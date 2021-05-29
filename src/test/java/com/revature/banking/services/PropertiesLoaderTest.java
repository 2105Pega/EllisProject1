package com.revature.banking.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PropertiesLoaderTest {
	@Test
	public void getSecretKey() {
		Assertions.assertNotNull(PropertiesLoader.getSecretKey());
	}
}
