package com.monolytum.heatmap;

import org.bukkit.Location;

import java.util.HashMap;

public class HeatMap2D implements BaseHeatMap {

	private HashMap<Integer, int[]> data = new HashMap<>();

	@Override
	public void addAt(Location location, int value) {
		if (!isChunkPresent(location)) data.put(getHash(location), new int[256]);
		data.get(getHash(location))[getIndex(location)] += value;
	}

	@Override
	public void setValueAt(Location location, int value) {
		if (!isChunkPresent(location)) data.put(getHash(location), new int[256]);
		data.get(getHash(location))[getIndex(location)] = value;
	}

	@Override
	public int getValueAt(Location location) {
		if (!isChunkPresent(location)) return 0;
		return data.get(getHash(location))[getIndex(location)];
	}

	private boolean isChunkPresent(Location location) {
		return data.keySet().contains(getHash(location));
	}

	private static int getHash(Location location) {
		return location.getChunk().getX() << 16 + location.getChunk().getZ();
	}

	private int getIndex(Location location) {
		return location.getBlockX() % 16 + 16 * (location.getBlockY() % 16);
	}
}
