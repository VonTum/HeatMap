package com.monolytum.heatmap;

import org.bukkit.Location;

public interface HeatMapStorage {
	default void addAt(Location l, int value){
		setValueAt(l, getValueAt(l) + value);
	}
	void setValueAt(Location l, int value);
    int getValueAt(Location l);
    void reset();
}
