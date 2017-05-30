package com.monolytum.heatmap;

import org.bukkit.Location;

import java.util.HashMap;

public class HeatMapSparse2D implements DataStorage {

	public HashMap<Position, Integer> data = new HashMap<>();

	@Override
	public void addAt(Location location, int value) {
		if (!isPositionPresent(location)) data.put(new Position(location), 0);
		data.put(new Position(location), data.get(new Position(location)) + value);
	}

	@Override
	public void setValueAt(Location location, int value) {
		data.put(new Position(location), value);
	}

	@Override
	public int getValueAt(Location location) {
		if(!isPositionPresent(location)) return 0;
		return data.get(new Position(location));
	}

	private boolean isPositionPresent(Location location) {
		return data.containsKey(new Position(location));
	}

	public class Position {

		int x;
		int z;

		public Position(int x, int z) {
			this.x = x;
			this.z = z;
		}

		public Position(Location location) {
			this.x = location.getBlockX();
			this.z = location.getBlockZ();
		}

		@Override
		public boolean equals(Object obj) {
			return obj instanceof Position && ((Position) obj).x == x && ((Position) obj).z == z;
		}
	}
}
