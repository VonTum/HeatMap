package com.monolytum.heatmap;

import org.bukkit.Location;

import java.util.HashMap;

public class HeatMap2D implements DataStorage {

	private HashMap<ChunkPosition, byte[]> data = new HashMap<>();

	@Override
	public void addAt(Location location, int value) {
		if (!isChunkPresent(location)) data.put(new ChunkPosition(location), new byte[256]);
		data.get(new ChunkPosition(location.getBlockX(), location.getBlockZ()))[getIndex(location)] += value;
	}

	@Override
	public void setValueAt(Location location, int value) {
		if (!isChunkPresent(location)) data.put(new ChunkPosition(location), new byte[256]);
		data.get(new ChunkPosition(location.getBlockX(), location.getBlockZ()))[getIndex(location)] = (byte) value;
	}

	@Override
	public int getValueAt(Location location) {
		if (!isChunkPresent(location)) return 0;
		return data.get(new ChunkPosition(location.getBlockX(), location.getBlockZ()))[getIndex(location)];
	}

	private boolean isChunkPresent(Location location) {
		return data.keySet().contains(new ChunkPosition(location.getBlockX(), location.getBlockZ()));
	}

	private static int getIndex(Location location) {
		return location.getBlockX() % 16 + 16 * (location.getBlockY() % 16);
	}

	public class ChunkPosition {

		int x;
		int z;

		public ChunkPosition(int x, int z) {
			this.x = x;
			this.z = z;
		}

		public ChunkPosition(Location location) {
			this.x = location.getBlockX();
			this.z = location.getBlockZ();
		}

		@Override
		public int hashCode() {
			return x << 16 + z;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof ChunkPosition) {
				return ((ChunkPosition) obj).x == x && ((ChunkPosition) obj).z == z;
			}
			return false;
		}
	}
}
