package me.dasnasu.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dasnasu.playtimelog.PlayTimeLog;

public class Cmd_playtime_reset{
	

	public Cmd_playtime_reset(PlayTimeLog ptl, CommandSender sender, String args[]) {
		this.triggerCommand(ptl, sender, args);
	}
	
	public boolean triggerCommand(PlayTimeLog ptl, CommandSender sender, String args[]) {
		if(args.length == 1) {
			Player target  = ptl.getServer().getPlayer(args[0]);
			if(target instanceof Player) {
				ptl.setPlayTime(target, 0);
				sender.sendMessage(ChatColor.DARK_GREEN+"Successfully resetted "+target.getName()+"'s playtime.");
			}
		}
		else if(args.length == 0) {
			ptl.setPlayTime((Player)sender, 0);
			sender.sendMessage(ChatColor.DARK_GREEN+"Successfully resetted your playtime.");
		}
		return true;
	}
}
