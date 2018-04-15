package me.dasnasu.playtimelog;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.dasnasu.commands.Cmd_playtime_add;
import me.dasnasu.commands.Cmd_playtime_reset;
import me.dasnasu.commands.Cmd_playtime_set;
import me.dasnasu.commands.Cmd_playtime_show;


public class PlayTimeLog extends JavaPlugin {
	private final int timeout = getConfig().getInt("timeout");
	

	public void onEnable() {
		System.out.println("[PlayTime] Plugin successfully loaded!");
		initialiseConfig();
		this.getCommand("ptshow").setPermissionMessage(ChatColor.RED+"Sorry, but you aren't allowed to do that.");
		this.getCommand("ptshowp").setPermissionMessage(ChatColor.RED+"Sorry, but you aren't allowed to do that.");
		this.getCommand("ptset").setPermissionMessage(ChatColor.RED+"Sorry, but you aren't allowed to do that.");
		this.getCommand("ptsetp").setPermissionMessage(ChatColor.RED+"Sorry, but you aren't allowed to do that.");
		this.getCommand("ptadd").setPermissionMessage(ChatColor.RED+"Sorry, but you aren't allowed to do that.");
		this.getCommand("ptaddp").setPermissionMessage(ChatColor.RED+"Sorry, but you aren't allowed to do that.");
		this.getCommand("ptreset").setPermissionMessage(ChatColor.RED+"Sorry, but you aren't allowed to do that.");
		this.getCommand("ptresetp").setPermissionMessage(ChatColor.RED+"Sorry, but you aren't allowed to do that.");
		startTimeLogging();
	}
	
	public void onDisable() {
		System.out.println("[PlayTime] Plugin successfully disabled!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if(sender instanceof Player){
			switch (cmd.getName()) {
				case "ptshow":
					new Cmd_playtime_show(this, sender, args);
					break;
				case "ptshowp":
					new Cmd_playtime_show(this, sender, args);
					break;
				case "ptset":
					new Cmd_playtime_set(this, sender, args);
					break;
				case "ptsetp":
					new Cmd_playtime_set(this, sender, args);
					break;
				case "ptadd":
					new Cmd_playtime_add(this, sender, args);
					break;
				case "ptreset":
					new Cmd_playtime_reset(this, sender, args);
					break;
				case "ptaddp":
					new Cmd_playtime_add(this, sender, args);
					break;
				case "ptresetp":
					new Cmd_playtime_reset(this, sender, args);
					break;
				default:
					break;
			}
		}
		return true;
	}
	
	private void initialiseConfig() {
		if(getConfig().getBoolean("default") == true) {
			this.getConfig().options().copyDefaults(true);
			getConfig().set("default", false);
			saveConfig();
		}
	}
	
	public int getPlayTime(Player player) {
		int	time = this.getConfig().getInt("players."+player.getUniqueId()+".time");
		return time;
	}
	
	public void setPlayTime(Player player, int time) {
		getConfig().set("players."+player.getUniqueId()+".time", time);
		getConfig().set("players."+player.getUniqueId()+".nickname", player.getName());
		saveConfig();
	}
	
	public void startTimeLogging() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				for(Player players:Bukkit.getOnlinePlayers()) {
					int time = getPlayTime(players);
					time = time+timeout;
					setPlayTime(players, time);
				}
			}
			
		}, 20*timeout, 20*timeout);
	}
}
