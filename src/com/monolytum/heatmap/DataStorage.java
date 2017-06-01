package com.monolytum.heatmap;

import org.bukkit.Location;

public interface DataStorage {
	default void addAt(Location l, int value){
		setValueAt(l, getValueAt(l) + value);
	}
	void setValueAt(Location l, int value);
    int getValueAt(Location l);
}
