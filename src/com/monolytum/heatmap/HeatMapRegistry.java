package com.monolytum.heatmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.plugin.Plugin;

public class HeatMapRegistry {
	private HashMap<TwoPartName, HeatMapStorageFactory> heatMapStorageFactories = new HashMap<>();
	private HashMap<TwoPartName, HeatMapFillerFactory> heatMapFillerFactories = new HashMap<>();
	
	public void registerHeatMapStorage(String name, Plugin registringPlugin, HeatMapStorageFactory factory) {
		TwoPartName index = new TwoPartName(registringPlugin.getName(), name);
		
		heatMapStorageFactories.put(index, factory);
	}
	
	public void registerHeatMapFiller(String name, Plugin registringPlugin, HeatMapFillerFactory factory) {
		TwoPartName index = new TwoPartName(registringPlugin.getName(), name);
		
		heatMapFillerFactories.put(index, factory);
	}
	
	public HeatMapStorage createNewHeatMapStorage(String name, Map<String, Object> options){
		HeatMapStorageFactory factory = heatMapStorageFactories.get(new TwoPartName(name));
		if(factory == null)
			return null;
		else
			return factory.produce(options);
	}
	
	public HeatMapFiller createNewHeatMapFiller(String name, HeatMapStorage storage, Map<String, Object> options){
		HeatMapFillerFactory factory = heatMapFillerFactories.get(new TwoPartName(name));
		
		if(factory == null)
			return null;
		else
			return factory.produce(storage, options);
	}
	
	public Set<TwoPartName> getRegisteredStorages(){
		return heatMapStorageFactories.keySet();
	}
	
	public Set<TwoPartName> getRegisteredFillers(){
		return heatMapFillerFactories.keySet();
	}
	
	public static interface HeatMapStorageFactory{
		public HeatMapStorage produce(Map<String, Object> options);
	}
	
	public static interface HeatMapFillerFactory{
		public HeatMapFiller produce(HeatMapStorage storage, Map<String, Object> options);
	}
	
	public static class TwoPartName {
		public final String parent;
		public final String child;
		
		public TwoPartName(String parent, String child) {
			this.parent = parent;
			this.child = child;
		}
		
		public TwoPartName(String fullName){
			String[] parts = fullName.split(":", 2);
			if(parts.length == 0){
				this.parent = null;
				this.child = null;
			}else if(parts.length == 1){
				this.parent = null;
				this.child = parts[0];
			}else{
				this.parent = parts[0];
				this.child = parts[1];
			}
		}
		
		@Override
		public int hashCode(){
			return child.toLowerCase().hashCode();
		}
		
		@Override
		public String toString(){
			return parent + ":" + child;
		}
		
		@Override
		public boolean equals(Object o){
			TwoPartName other;
			
			if(o instanceof TwoPartName)
				other = (TwoPartName) o;
			else
				return false;
			
			
			// if parent defined, check, otherwise ignore
			if(this.parent != null && other.parent != null)
				if(!this.parent.equalsIgnoreCase(other.parent))
					return false;
			
			// compare children
			if(this.child != null && other.child != null)
				return this.child.equalsIgnoreCase(other.child);
			else
				return false;
			
			
		}
	}
}
