package com.monolytum.heatmap;

import org.bukkit.plugin.java.JavaPlugin;

public class HeatMap extends JavaPlugin{
	
	public static HeatMap plugin;
	
	//doing it quick and dirty, will make into real API later
	public HeatMap2D playerHeatMapStore;
	public PlayerListener playerListener;
	
	@Override
	public void onEnable(){
		plugin = this;
		
		HeatMapCommand hmCommand = new HeatMapCommand();
		HeatMapAdminCommand hmaCommand = new HeatMapAdminCommand();
		
		getCommand("heatmap").setExecutor(hmCommand);
		getCommand("heatmap").setTabCompleter(hmCommand);
		
		getCommand("heatmapadmin").setExecutor(hmaCommand);
		getCommand("heatmapadmin").setTabCompleter(hmaCommand);
		
		Config.reload(getConfig());
		Localization.reload(getConfig());
		
		playerHeatMapStore = new HeatMap2D();
		
		playerListener = new PlayerListener(playerHeatMapStore);
	}
	
	@Override
	public void onDisable(){
		plugin = null;
	}
}
