package com.monolytum.heatmap;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.plugin.Plugin;

public class HeatMapRegistry {
	private HashMap<String, HeatMapStorageFactory> heatMapStorageFactories = new HashMap<>();
	private HashMap<String, HeatMapFillerFactory> heatMapFillerFactories = new HashMap<>();
	
	public void registerHeatMapStorage(String name, Plugin registringPlugin, HeatMapStorageFactory factory) {
		heatMapStorageFactories.put(name.toLowerCase(), factory);
		
		//TODO include plugin in thingy
	}
	
	public void registerHeatMapFiller(String name, Plugin registringPlugin, HeatMapFillerFactory factory) {
		heatMapFillerFactories.put(name.toLowerCase(), factory);
		
		//TODO include plugin in thingy
	}
	
	public HeatMapStorage createNewHeatMapStorage(String name, Map<String, Object> options){
		return heatMapStorageFactories.get(name.toLowerCase()).produce(options);
	}
	
	public HeatMapFiller createNewHeatMapFiller(String name, HeatMapStorage storage, Map<String, Object> options){
		return heatMapFillerFactories.get(name.toLowerCase()).produce(storage, options);
	}
	
	public HeatMap createNewHeatMap(String heatMapName, String dataStorageName, String dataFillerName, Map<String, Object> storageOptions, Map<String, Object> fillerOptions) {
		HeatMapStorage storage = heatMapStorageFactories.get(dataStorageName.toLowerCase()).produce(storageOptions);
		HeatMapFiller filler = heatMapFillerFactories.get(dataFillerName.toLowerCase()).produce(storage, fillerOptions);
		return new HeatMap(heatMapName, storage, filler);
	}
	
	public static interface HeatMapStorageFactory{
		public HeatMapStorage produce(Map<String, Object> options);
	}
	
	public static interface HeatMapFillerFactory{
		public HeatMapFiller produce(HeatMapStorage storage, Map<String, Object> options);
	}
}
