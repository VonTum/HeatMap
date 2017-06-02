package com.monolytum.heatmap;

import com.monolytum.heatmap.IO.FileWriter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.HashMap;

public class HeatMapAdminCommand implements CommandExecutor, TabCompleter {
	private static final String[] TABCOMPLETE_LIST = {
		"disable",
		"debug",
		"enable",
		"reset",
		"save"
	};
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		if(args.length < 2) return false;
		
		switch(args[0]){
		case "save":
			FileWriter.writeToImage(HMPlugin.plugin.playerHeatMapStore, args[1]);
			sender.sendMessage("HeatMap '" + args[1] + "' has been written to " + args[1] + ".bmp");
			return true;
		case "debug":
			FileWriter.writeDebugFile(HMPlugin.plugin.playerHeatMapStore, args[1]);
			sender.sendMessage("HeatMap '" + args[1] + "' has been written to " + args[1] + ".txt");
			return true;
		case "reset":
			HMPlugin.plugin.playerHeatMapStore.data = new HashMap<HeatMap2D.ChunkPosition, byte[]>();
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
