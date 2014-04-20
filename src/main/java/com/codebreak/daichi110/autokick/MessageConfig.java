package com.codebreak.daichi110.autokick;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.codebreak.daichi110.autokick.Autokick;

public class MessageConfig {
	
	private Autokick plugin;
	
	private FileConfiguration messageConfig = null;
	private File messageConfigFile = null;
	
	public MessageConfig(Autokick plugin) {
		plugin = this.plugin;
	}
	
	public void reloadConfig() {
	    if (messageConfigFile == null) {
	        messageConfigFile = new File(plugin.getDataFolder(), "message.yml");
	    }
	    messageConfig = YamlConfiguration.loadConfiguration(messageConfigFile);

	    InputStream defConfigStream = plugin.getResource("message.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        messageConfig.setDefaults(defConfig);
	    }
	}
	
	public FileConfiguration getConfig() {
	    if (messageConfig == null) {
	        reloadConfig();
	    }
	    return messageConfig;
	}
	
	public void saveConfig() {
	    if (messageConfig == null || messageConfigFile == null) {
	        return;
	    }
	    try {
	        getConfig().save(messageConfigFile);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}

}
