package me.dasnasu.commands;

import java.util.regex.Pattern;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dasnasu.playtimelog.PlayTimeLog;

public class Cmd_playtime_add{
	

	public Cmd_playtime_add(PlayTimeLog ptl, CommandSender sender, String args[]) {
		this.triggerCommand(ptl, sender, args);
	}
	
	public boolean triggerCommand(PlayTimeLog ptl, CommandSender sender, String args[]) {
		Player target  = ptl.getServer().getPlayer(args[0]);
		if(target instanceof Player) {
			if(!Pattern.matches("[a-zA-Z]+",args[1]) == true) {
				int playtime = ptl.getPlayTime(target);
				int newplaytime = playtime+Integer.parseInt(args[1]);
				ptl.setPlayTime(target, newplaytime);
				sender.sendMessage(ChatColor.DARK_GREEN+"Successfully added "+Integer.parseInt(args[1])+" Seconds to "+target.getName()+"'s playtime.");
			}
		}
		else if(!Pattern.matches("[a-zA-Z]+", args[0]) == true) {
			int playtime = ptl.getPlayTime((Player)sender);
			int newplaytime = playtime+Integer.parseInt(args[0]);
			ptl.setPlayTime((Player)sender, newplaytime);
			sender.sendMessage(ChatColor.DARK_GREEN+"Successfully added "+Integer.parseInt(args[0])+" Seconds to your playtime.");
		}
		return true;
	}
}
