package me.dasnasu.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dasnasu.playtimelog.PlayTimeLog;

public class Cmd_playtime_show {
	private PlayTimeLog ptl;
	
	public Cmd_playtime_show(PlayTimeLog ptl, CommandSender sender, String args[]) {
		this.ptl = ptl;
		if(args.length == 0) {
			this.triggerCommand(sender);
		}
		else {
			this.triggerCommand(sender, args);
		}
	}
	
	public boolean triggerCommand(CommandSender sender) {
		Player player = (Player)sender;
		outputPlayTime(sender, player);
		return true;
	}
	
	public boolean triggerCommand(CommandSender sender, String args[]) {
		Player target = ptl.getServer().getPlayer(args[0]);
		if(target instanceof Player) {
			outputPlayTime(sender, target);
		}
		return true;
	}
	
	public void outputPlayTime(CommandSender cmdsender, Player player) {
		double time = ptl.getPlayTime(player);
		int days = (int) Math.floor(time/86400);
		int hours = (int) Math.floor((time-(days*86400))/3600);
		int minutes = (int) Math.floor(((time-(days*86400)-(hours*3600)))/60);
		Player sender = ptl.getServer().getPlayer(cmdsender.getName());
		if(!sender.equals(player)) {
			cmdsender.sendMessage("The overall playtime of "+player.getName()+" on this server is:\n"+days+"days, "+hours+"hours and "+minutes+"minutes.");
		} else {
			player.sendMessage("Your overall playtime on this server is:\n"+days+"days, "+hours+"hours and "+minutes+"minutes.");
		}
	}
}
