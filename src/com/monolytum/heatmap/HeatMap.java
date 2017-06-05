package com.monolytum.heatmap;

public class HeatMap {
	private final String name;
	private final HeatMapStorage storage;
	private final HeatMapFiller filler;
	
	private boolean enabled;
	
	public HeatMap(String name, HeatMapStorage storage, HeatMapFiller filler){
		this.name = name;
		this.storage = storage;
		this.filler = filler;
		this.enabled = false;
	}
	
	public void enable(){
		this.enabled = true;
		filler.enable();
	}
	
	public void disable(){
		this.enabled = false;
		filler.disable();
	}
	
	public void reset(){
		storage.reset();
	}
	
	public boolean isEnabled(){return enabled;}
	public String getName(){return name;}
	
}
