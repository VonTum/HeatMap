package com.monolytum.heatmap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;

public class HeatMapCommand implements CommandExecutor, TabCompleter {
	private static final String[] TABCOMPLETE_LIST = {
		"opt-out",
		"opt-in"
	};

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		if(args.length < 2) return false;
		
		return false;
	}
	
	@Override
	public ArrayList<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		ArrayList<String> currentTabComplete = new ArrayList<>();
		switch(args.length){
		case 1:
			for(String s:TABCOMPLETE_LIST)
				if(s.startsWith(args[0]))
					currentTabComplete.add(s);
			break;
		}
		
		return currentTabComplete;
	}
	
}
