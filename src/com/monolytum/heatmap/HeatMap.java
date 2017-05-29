package com.monolytum.heatmap;

import org.bukkit.plugin.java.JavaPlugin;

public class HeatMap extends JavaPlugin {

	public static HeatMap heatMap;
	public HeatMap2D playerHeatMapStore;
	public PlayerMovementMap playerMovementMap;
	
	@Override
	public void onEnable(){
		Config.reload(getConfig());

		heatMap = this;

		playerHeatMapStore = new HeatMap2D();
		
		playerMovementMap = new PlayerMovementMap(playerHeatMapStore);
	}
	
	@Override
	public void onDisable(){
		
	}
}
