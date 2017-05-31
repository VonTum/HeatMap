package com.monolytum.heatmap;

import org.bukkit.Location;

import java.util.HashMap;

public class HeatMap2D implements DataStorage {

	public HashMap<ChunkPosition, byte[]> data = new HashMap<>();

	@Override
	public void addAt(Location location, int value) {
		createIfNotExist(location);
		
		data.get(new ChunkPosition(location))[getIndex(location)] += value;
	}

	@Override
	public void setValueAt(Location location, int value) {
		createIfNotExist(location);
		
		data.get(new ChunkPosition(location))[getIndex(location)] = (byte) value;
	}

	@Override
	public int getValueAt(Location location) {
		if (!isChunkPresent(location))
			return 0;
		
		System.out.println("Index: " + getIndex(location));
		
		return data.get(new ChunkPosition(location))[getIndex(location)];
	}

	private boolean isChunkPresent(Location location) {
		return data.containsKey(new ChunkPosition(location));
	}

	private static int getIndex(Location location) {
		return (location.getBlockX() & 0x0F) + (location.getBlockZ() & 0x0F) * 16;
	}
	
	private void createIfNotExist(Location l){
		if (!isChunkPresent(l))
			data.put(new ChunkPosition(l), new byte[256]);
	}

	public static class ChunkPosition {
		public int x;
		public int z;

		public ChunkPosition(int x, int z) {
			this.x = x;
			this.z = z;
		}

		public ChunkPosition(Location location) {
			this.x = location.getChunk().getX();
			this.z = location.getChunk().getZ();
		}

		@Override
		public int hashCode() {
			return x << 16 + z;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof ChunkPosition) {
				ChunkPosition other = (ChunkPosition) obj;
				return other.x == x && other.z == z;
			}
			return false;
		}
	}
}
