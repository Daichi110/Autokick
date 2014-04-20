package com.codebreak.daichi110.autokick;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class AutokickListener implements Listener {
	
	private Autokick plugin;

	public AutokickListener(Autokick plugin) {
		plugin = this.plugin;
	}
	
	  @EventHandler
	  public void onPlayerLogin(PlayerLoginEvent event) {
	    if (!plugin.status) return;
	    
	    Player player = event.getPlayer();
	    
	    if (player.hasPermission("autokick.ignore")) return;
	    
	    event.disallow(PlayerLoginEvent.Result.KICK_OTHER, plugin.msg);
	  }
}
