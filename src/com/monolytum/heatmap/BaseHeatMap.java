package com.monolytum.heatmap;

import org.bukkit.Location;

//base for all heatmaps, genericly named for potential further use outside of heatmaps
public interface BaseHeatMap {
	public void addAt(Location l, int value);
	public void setValueAt(Location l, int value);
	public int getValueAt(Location l);
}
