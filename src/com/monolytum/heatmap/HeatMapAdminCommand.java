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
		switch(args[0]){
		case "save":{
			// TODO save
			if(args.length < 2) return false;
			HeatMap hm = HMPlugin.plugin.getHeatMap(args[1]);
			sender.sendMessage("HeatMap '" + args[1] + "' has been written to " + args[1] + ".bmp");
			return true;
		}
		case "debug":{
			// TODO debug
			if(args.length < 2) return false;
			HeatMap hm = HMPlugin.plugin.getHeatMap(args[1]);
			sender.sendMessage("HeatMap '" + args[1] + "' has been written to " + args[1] + ".txt");
			return true;
		}
		case "reset":{
			if(args.length < 2) return false;
			HeatMap hm = HMPlugin.plugin.getHeatMap(args[1]);
			hm.reset();
			sender.sendMessage("HeatMap '" + args[1] + "' has been reset!");
			return true;
		}
		case "list":{
			String msg = "HeatMaps:\n";
			for(HeatMap heatMap:HMPlugin.plugin.getHeatMaps())
				msg += heatMap.getName() + ", ";
			
			if(HMPlugin.plugin.getHeatMaps().size() == 0)
				sender.sendMessage("There are no heatmaps registered!");
			else
				sender.sendMessage(msg.substring(0, msg.length() - 2));
			return true;
			}
		case "listregistry":{
			String msg = "HeatMapFillers:\n";
			for(HeatMapRegistry.TwoPartName filler:HMPlugin.plugin.getRegistry().getRegisteredFillers())
				msg += filler.toString() + "\n";
			msg += "HeatMapStorages:\n";
			for(HeatMapRegistry.TwoPartName storage:HMPlugin.plugin.getRegistry().getRegisteredStorages())
				msg += storage.toString() + "\n";
			
			sender.sendMessage(msg.substring(0, msg.length() - 1));
		}
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
