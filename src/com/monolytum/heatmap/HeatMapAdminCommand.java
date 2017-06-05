package com.monolytum.heatmap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;

public class HeatMapAdminCommand implements CommandExecutor, TabCompleter {
	private static final String[] TABCOMPLETE_LIST = {
		"disable",
		"debug",
		"enable",
		"reset",
		"save",
		"list"
	};
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		if(args.length < 2) return false;
		
		HeatMap hm = HMPlugin.plugin.getHeatMap(args[1]);
		
		switch(args[0]){
		case "save":
			// TODO save
			sender.sendMessage("HeatMap '" + args[1] + "' has been written to " + args[1] + ".bmp");
			return true;
		case "debug":
			// TODO debug
			sender.sendMessage("HeatMap '" + args[1] + "' has been written to " + args[1] + ".txt");
			return true;
		case "reset":
			hm.reset();
			sender.sendMessage("HeatMap '" + args[1] + "' has been reset!");
			return true;
		}
		return false;
	}
	
	@Override
	public ArrayList<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		ArrayList<String> currentTabComplete = new ArrayList<>();
		switch(args.length) {
		case 1:
			for(String s:TABCOMPLETE_LIST)
				if(s.startsWith(args[0]))
					currentTabComplete.add(s);
			break;
		}
		
		return currentTabComplete;
	}

}
