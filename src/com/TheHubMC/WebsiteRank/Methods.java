package com.TheHubMC.WebsiteRank;

import com.TheHubMC.WebsiteRank.sql.DBConnection;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Random;

public class Methods {
	
	private static HashMap<Integer, Character> alphabet = new HashMap<>();
	
	static {
		char letter = 'a';
		alphabet.put(10, letter);
		for (int i = 11; i < 36; i++) {
			letter++;
			alphabet.put(i, letter);
		}
	}

	public static String generateCode() {
		int max = 36;
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i < 10; i++) {
			int I = rand.nextInt(max);
			
			if (I < 10) {
				sb.append(I);
				continue;
			} else {
				char letter = alphabet.get(I);
				double random = Math.random();
				if (random > 0.5) {
					letter = Character.toUpperCase(letter);
				}
				sb.append(letter);
				continue;
			}
		}
		
		String code = sb.toString();
		if (!codeExists(code)) {
			return code;
		} else {
			return generateCode();
		}
	}
	
	public static boolean codeExists(String code) {
		ResultSet rs = DBConnection.sql.readQuery("SELECT code FROM ranks");
		try {
			if (rs.next()) {
				String check = rs.getString(1);
				if (check.equals(code)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}
	
	public static void assignCode(String uuid, String code) {
		ResultSet rs = DBConnection.sql.readQuery("SELECT * FROM ranks WHERE uuid = " + uuid);
		try {
			if (!rs.next()) {
				DBConnection.sql.modifyQuery("INSERT INTO ranks (uuid, code) VALUES (" + uuid + ", " + code + ")");
			} else {
				DBConnection.sql.modifyQuery("UPDATE ranks SET code = " + code);
			}
			WebsiteRank.log.info("Player with uuid " + uuid + " was assigned the code " + code);
		} catch (Exception e) {
			WebsiteRank.log.severe("Unable to update player's code");
			e.printStackTrace();
			return;
		}
	}
	
	public static void assignRank(String uuid, String rank) {
		ResultSet rs = DBConnection.sql.readQuery("SELECT * FROM ranks WHERE uuid = " + uuid);
		try {
			if (!rs.next()) {
				DBConnection.sql.modifyQuery("INSERT INTO ranks (uuid, rank) VALUES (" + uuid + ", " + rank + ")");
			} else {
				DBConnection.sql.modifyQuery("UPDATE ranks SET rank = " + rank + " WHERE uuid = " + uuid);
			}
			WebsiteRank.log.info("Player with uuid " + uuid + " was assigned the rank " + rank);
		} catch (Exception e) {
			WebsiteRank.log.severe("Unable to update player's rank");
			e.printStackTrace();
			return;
		}
	}
	
	public static boolean hasCode(String uuid) {
		ResultSet rs = DBConnection.sql.readQuery("SELECT code FROM ranks WHERE uuid = " + uuid);
		try {
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}
	
	public static boolean hasRank(String uuid) {
		ResultSet rs = DBConnection.sql.readQuery("SELECT rank FROM ranks WHERE uuid = " + uuid);
		try {
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}
}
