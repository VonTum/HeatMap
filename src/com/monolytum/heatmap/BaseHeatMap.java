package com.monolytum.heatmap;

import org.bukkit.Location;

//base for all heatmaps, genericly named for potential further use outside of heatmaps
interface BaseHeatMap {
	void addAt(Location l, int value);
	void setValueAt(Location l, int value);
	int getValueAt(Location l);
}
