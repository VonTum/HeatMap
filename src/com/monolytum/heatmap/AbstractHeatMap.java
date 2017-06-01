package com.monolytum.heatmap;

abstract class AbstractHeatMap {
	protected DataStorage map;
	
	public AbstractHeatMap(DataStorage map) {
		this.map = map;
	}
	
	abstract void enable();
	abstract void disable();
	
	public DataStorage getDataStore(){return map;}
}
