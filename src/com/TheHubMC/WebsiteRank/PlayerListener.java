package com.TheHubMC.WebsiteRank;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener{

	@EventHandler
	public void onLogin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String uuid = player.getUniqueId().toString();
		if (!Methods.hasCode(uuid)) {
			Methods.assignCode(uuid, Methods.generateCode());
		}
		if (!Methods.hasRank(uuid)) {
			Methods.assignRank(uuid, "Member");
		}
	}
}
