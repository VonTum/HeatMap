package com.monolytum.heatmap;

import org.bukkit.plugin.java.JavaPlugin;

public class HeatMap extends JavaPlugin{
	
	//doing it quick and dirty, will make into real API later
	public HeatMap2D playerHeatMapStore;
	public PlayerListener playerListener;
	
	@Override
	public void onEnable(){
		Config.reload(getConfig());
		
		playerHeatMapStore = new HeatMap2D();
		
		playerListener = new PlayerListener(playerHeatMapStore);
	}
	
	@Override
	public void onDisable(){
		
	}
}
