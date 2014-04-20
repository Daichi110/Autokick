package com.codebreak.daichi110.autokick;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AutokickCommand implements CommandExecutor {

	private Autokick plugin;
	private String PREFIX = ChatColor.AQUA + "[Autokick] ";

	public AutokickCommand(Autokick plugin) {
		plugin = this.plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
		if (args.length < 1)
			return false;

		if (args[0].equalsIgnoreCase("reload")) {
			plugin.reloadConfig();
			plugin.message.reloadConfig();
			plugin.msg = ChatColor.translateAlternateColorCodes(
					'&',
					plugin.message.getConfig().getString("message")
							.replaceAll("&NL", "\n"));
		} else if (args[0].equalsIgnoreCase("on")) {
			if (plugin.status) {
				sender.sendMessage(PREFIX + "すでに有効です。");
				return true;
			}
			plugin.status = true;
			sender.sendMessage(PREFIX + "有効にしました。");
			return true;
		} else if (args[0].equalsIgnoreCase("off")) {
			if (!plugin.status) {
				sender.sendMessage(PREFIX + "すでに無効です。");
				return true;
			}
			plugin.status = false;
			sender.sendMessage(PREFIX + "無効にしました。");
			return true;
		} else if(args[0].equalsIgnoreCase("status")) {
			if (plugin.status) {
				sender.sendMessage(PREFIX + "有効です。");
				return true;
			}else{
			sender.sendMessage(PREFIX + "無効です。");
			return true;
			}
		} else if(args.length >= 2 && args[0].equalsIgnoreCase("msg")) {
			if(args[1].equalsIgnoreCase("default")) {
				plugin.message.getConfig().set("message", plugin.getConfig().getString("message"));
				plugin.message.saveConfig();
				sender.sendMessage(PREFIX + "メッセージをデフォルトに変更しました。");
			}else if(args.length >= 3 && args[0].equals("set")) {
				plugin.message.getConfig().set("message", args[2]);
				plugin.message.saveConfig();
				sender.sendMessage(PREFIX + "メッセージを/n" + ChatColor.RESET  + plugin.message.getConfig().getString("message") + "/nに変更しました。");
			}
		}
		return false;
	}

}
