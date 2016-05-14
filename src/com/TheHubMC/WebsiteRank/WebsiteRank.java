package com.TheHubMC.WebsiteRank;

import com.TheHubMC.WebsiteRank.sql.DBConnection;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class WebsiteRank extends JavaPlugin{
	
	public static WebsiteRank plugin;
	public static Logger log;
	
	@Override
	public void onEnable() {
		plugin = this;
		log = this.getLogger();
		
		FileConfiguration config = getConfig();
		config.addDefault("Storage.engine", "sqlite");

		config.addDefault("Storage.MySQL.host", "localhost");
		config.addDefault("Storage.MySQL.port", 3306);
		config.addDefault("Storage.MySQL.pass", "");
		config.addDefault("Storage.MySQL.db", "minecraft");
		config.addDefault("Storage.MySQL.user", "root");
		
		DBConnection.init();
	}
}
