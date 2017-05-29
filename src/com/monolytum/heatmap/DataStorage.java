package com.monolytum.heatmap;

import org.bukkit.Location;

public interface DataStorage {

	void addAt(Location l, int value);
	void setValueAt(Location l, int value);
    int getValueAt(Location l);

}
