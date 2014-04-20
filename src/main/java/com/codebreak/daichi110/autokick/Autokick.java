package com.codebreak.daichi110.autokick;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Autokick extends JavaPlugin {
	
	public Autokick plugin;
	
	public MessageConfig message;
	
	public boolean status;
	public String msg;
	
	@Override
	public void onEnable() {
	    plugin = this;
	    
	    message = new MessageConfig(this);
	    
	    saveDefaultConfig();
	    
	    if(message.getConfig().getString("message") == null) {
	    	message.getConfig().set("message", getConfig().getString("message"));
	    	plugin.message.saveConfig();
	    }
	    
	    status = getConfig().getBoolean("default", true);
	    
	    msg = ChatColor.translateAlternateColorCodes('&', message.getConfig().getString("message").replaceAll("&NL", "\n"));
	    
	    getCommand("autokick").setExecutor(new AutokickCommand(this));
	    
	    getServer().getPluginManager().registerEvents(new AutokickListener(this), this);
	}
	
}
