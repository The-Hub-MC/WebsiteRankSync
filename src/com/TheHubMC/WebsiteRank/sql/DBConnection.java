package com.TheHubMC.WebsiteRank.sql;

import com.TheHubMC.WebsiteRank.WebsiteRank;

public class DBConnection {

	public static Database sql;
	public static String engine;
	public static String host;
	public static int port;
	public static String db;
	public static String user;
	public static String pass;
	public static boolean isOpen = false;
	
	public static void init() {
		if (open()) {
			if (!sql.tableExists("ranks")) {
				String query = "CREATE TABLE ranks (uuid %uuid, rank %rank, code %code)";
				if (engine.equalsIgnoreCase("mysql")) {
					query.replace("%uuid", "varchar(36)");
					query.replace("%rank", "varchar(255)");
					query.replace("%code", "varchar(9)");
				} else {
					query.replace("%uuid", "TEXT(36)");
					query.replace("%rank", "TEXT(255)");
					query.replace("%code", "TEXT(9)");
				}
			}
		}
	}
	
	public static boolean open() {
		if (engine.equalsIgnoreCase("mysql")) {
			sql = new MySQL(WebsiteRank.log, "Establishing MySQL Connection...", host, port, user, pass, db);
			if (((MySQL) sql).open() == null) {
				WebsiteRank.log.severe("Disabling due to database error");
				return false;
			}
			WebsiteRank.log.info("Database connection established.");
		} else {
			sql = new SQLite(WebsiteRank.log, "Establishing SQLite Connection.", "projectkorra.db", WebsiteRank.plugin.getDataFolder().getAbsolutePath());
			if (((SQLite) sql).open() == null) {
				WebsiteRank.log.severe("Disabling due to database error");
				return false;
			}
		}
		isOpen = true;
		return true;
	}
	
	public static void close() {
		if (isOpen) {
			sql.close();
		}
	}
	
	public static boolean isOpen() {
		return isOpen;
	}
}
