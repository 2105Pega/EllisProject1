package com.revature.banking.services;

import java.security.MessageDigest;

public class SHA256 {
	private SHA256() {}
	
	public static String hash(String password) {
        String hash = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            byte[] digest = messageDigest.digest();
            hash = bytesToHexString(digest);
        } catch (Exception e) {
            System.out.println("password hashing failed");
        }
        return hash;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder s = new StringBuilder("");
        for (byte b : bytes) {
            byte upperNibble = (byte)(b & 0x0F);
            byte lowerNibble = (byte)((b & 0xF0) >> 4);

            s.append(nibbleToChar(lowerNibble));
            s.append(nibbleToChar(upperNibble));
        }
        return s.toString();
    }

    private static Character nibbleToChar(byte b) {
        switch (b) {
            case 0x0:
                return '0';
            case 0x1:
                return '1';
            case 0x2:
                return '2';
            case 0x3:
                return '3';
            case 0x4:
                return '4';
            case 0x5:
                return '5';
            case 0x6:
                return '6';
            case 0x7:
                return '7';
            case 0x8:
                return '8';
            case 0x9:
                return '9';
            case 0xA:
                return 'A';
            case 0xB:
                return 'B';
            case 0xC:
                return 'C';
            case 0xD:
                return 'D';
            case 0xE:
                return 'E';
            case 0xF:
                return 'F';
        }
        return 'X';
    }
}
