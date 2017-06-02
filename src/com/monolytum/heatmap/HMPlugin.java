package com.monolytum.heatmap;

import org.bukkit.plugin.java.JavaPlugin;

//doing it quick and dirty, will make into real API later
public class HMPlugin extends JavaPlugin{
	
	public static HMPlugin plugin;
	public HeatMap2D playerHeatMapStore;
	public PlayerMovementMap playerMovementMap;
	
	@Override
	public void onEnable(){
		plugin = this;
		
		saveDefaultConfig();
		
		HeatMapCommand hmCommand = new HeatMapCommand();
		HeatMapAdminCommand hmaCommand = new HeatMapAdminCommand();
		
		getCommand("heatmap").setExecutor(hmCommand);
		getCommand("heatmap").setTabCompleter(hmCommand);
		
		getCommand("heatmapadmin").setExecutor(hmaCommand);
		getCommand("heatmapadmin").setTabCompleter(hmaCommand);
		
		Config.reload(getConfig());
		Localization.reload(getConfig());

		playerHeatMapStore = new HeatMap2D();
		
		playerMovementMap = new PlayerMovementMap(playerHeatMapStore);
		
		playerMovementMap.enable();
	}
	
	@Override
	public void onDisable(){
		plugin = null;
	}
}
