package com.revature.banking.services;

import java.io.FileReader;

import java.util.Properties;

public class PropertiesLoader {
	private static String secretKey = null;
	
    static void load() {
        try {
        	String propertiesPath = Thread.currentThread().getContextClassLoader().getResource("jdbcbank.properties").getPath();
            Properties properties = new Properties();
            FileReader fr = new FileReader(propertiesPath);
            properties.load(fr);
            secretKey = properties.getProperty("secretKey");
            fr.close();
        } catch (Exception e) {
            //logger.error("failed to load properties file");
        }
    }
    
    public static String getSecretKey() {
    	if (secretKey == null) {
    		load();
    		return secretKey;
    	} else {
        	return secretKey;
    	}
    }

}
