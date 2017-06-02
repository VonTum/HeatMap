package com.monolytum.heatmap;

abstract class HeatMapFiller {
	protected HeatMapStorage map;
	
	public HeatMapFiller(HeatMapStorage map) {
		this.map = map;
	}
	
	abstract void enable();
	abstract void disable();
	
	public HeatMapStorage getDataStore(){return map;}
}
