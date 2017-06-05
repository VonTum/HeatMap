package com.monolytum.heatmap;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;

import com.monolytum.heatmap.HeatMapRegistry.HeatMapFillerFactory;
import com.monolytum.heatmap.HeatMapRegistry.HeatMapStorageFactory;

//doing it quick and dirty, will make into real API later
public class HMPlugin extends JavaPlugin{
	
	public static HMPlugin plugin;
	
	private HeatMapRegistry registry = new HeatMapRegistry();
	
	private HashMap<String, HeatMap> heatMaps = new HashMap<String, HeatMap>();
	
	@Override
	public void onEnable(){
		plugin = this;
		
		initializeRegistry();
		
		saveDefaultConfig();
		
		HeatMapCommand hmCommand = new HeatMapCommand();
		HeatMapAdminCommand hmaCommand = new HeatMapAdminCommand();
		
		getCommand("heatmap").setExecutor(hmCommand);
		getCommand("heatmap").setTabCompleter(hmCommand);
		
		getCommand("heatmapadmin").setExecutor(hmaCommand);
		getCommand("heatmapadmin").setTabCompleter(hmaCommand);
		
		Config.reload(getConfig());
		Localization.reload(getConfig());
		
		/*playerHeatMapStore = new HeatMap2D();
		
		playerMovementMap = new PlayerMovementMap(playerHeatMapStore);
		
		playerMovementMap.enable();*/
		
		HeatMapStorage storage = registry.createNewHeatMapStorage("heatMap2D", Collections.emptyMap());
		HeatMapFiller filler = registry.createNewHeatMapFiller("playerMovement", storage, Collections.emptyMap());
		
		HeatMap playerMovementMap = new HeatMap("playerMove", storage, filler);
		
		addHeatMap(playerMovementMap);
		
		playerMovementMap.enable();
	}
	
	private void initializeRegistry(){
		registry.registerHeatMapFiller("playerMovement", this, new HeatMapFillerFactory() {
			@Override public HeatMapFiller produce(HeatMapStorage storage, Map<String, Object> options) {return new PlayerMovementMap(storage);}});
		registry.registerHeatMapFiller("playerTeleport", this, new HeatMapFillerFactory() {
			@Override public HeatMapFiller produce(HeatMapStorage storage, Map<String, Object> options) {return new PlayerMovementMap(storage);}});
		
		registry.registerHeatMapStorage("heatMap2D", this, new HeatMapStorageFactory() {
			@Override public HeatMapStorage produce(Map<String, Object> options) {return new HeatMap2D();}});
		registry.registerHeatMapStorage("heatMapSparse2D", this, new HeatMapStorageFactory() {
			@Override public HeatMapStorage produce(Map<String, Object> options) {return new HeatMap2D();}});
	}
	
	@Override
	public void onDisable(){
		plugin = null;
	}
	
	public HeatMap getHeatMap(String name){return heatMaps.get(name);}
	public void addHeatMap(HeatMap hm){heatMaps.put(hm.getName(), hm);}
	public boolean deleteHeatMap(String name){return heatMaps.remove(name) != null;}
	public Collection<HeatMap> getHeatMaps() {return heatMaps.values();}
	public HeatMapRegistry getRegistry(){return registry;}
}
