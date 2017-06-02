package com.monolytum.heatmap.IO;


import com.monolytum.heatmap.HMPlugin;
import com.monolytum.heatmap.HeatMap2D;
import com.monolytum.heatmap.HeatMapSparse2D;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter {
	public static void writeToImage(HeatMap2D map, String fileName) {
		System.out.println("Writing to file");
		int minX = 0;
		int minZ = 0;
		int maxX = 0;
		int maxZ = 0;
		
		for(HeatMap2D.ChunkPosition chunkPosition : map.data.keySet()) {
			minX = (chunkPosition.x < minX)? chunkPosition.x : minX;
			minZ = (chunkPosition.z < minZ)? chunkPosition.z : minZ;
			maxX = (chunkPosition.x > maxX)? chunkPosition.x : maxX;
			maxZ = (chunkPosition.z > maxZ)? chunkPosition.z : maxZ;
		}
		
		System.out.println("minX: " + minX);
		System.out.println("minZ: " + minZ);
		System.out.println("maxX: " + maxX);
		System.out.println("maxZ: " + maxZ);
		
		int width = (maxX - minX + 1) * 16;
		int height = (maxZ - minZ + 1) * 16;
		
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for (int chunkZ = minZ; chunkZ <= maxZ; chunkZ++) {
			for (int chunkX = minX; chunkX <= maxX; chunkX++) {

				HeatMap2D.ChunkPosition chunkPosition = new HeatMap2D.ChunkPosition(chunkX, chunkZ);

				if (map.data.containsKey(chunkPosition)) {
					byte[] blockValues = map.data.get(chunkPosition);
					for (int x = 0; x < 16; x++) {
						for (int z = 0; z < 16; z++) {
							int index = 16 * z + x;
							
							int value = 255-blockValues[index] * 10;
							if(value < 0) value = 0;
							Color color = new Color(value, value, value);
							
							image.setRGB(16 * (chunkX - minX) + x, 16 * (chunkZ - minZ) + z, color.getRGB());
						}
					}
				} else {
					for (int x = 0; x < 16; x++) {
						for (int z = 0; z < 16; z++) {
							image.setRGB(16 * (chunkX - minX) + x, 16 * (chunkZ - minZ) + z, Color.white.getRGB());
						}
					}
				}
			}
		}
		
		File outputFile = new File(HMPlugin.plugin.getDataFolder(), fileName + ".bmp");
		try {
			ImageIO.write(image, "bmp", outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeToImage(HeatMapSparse2D map, String fileName) {
		System.out.println("Writing to file");

		int minX = 0;
		int minZ = 0;
		int maxX = 0;
		int maxZ = 0;

		for(HeatMapSparse2D.Position position : map.data.keySet()) {
			minX = (position.x < minX)? position.x : minX;
			minZ = (position.z < minZ)? position.z : minZ;
			maxX = (position.x > maxX)? position.x : maxX;
			maxZ = (position.z > maxZ)? position.z : maxZ;
		}

		int width = (maxX - minX);
		int height = (maxZ - minX);

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++) {
			for (int z = 0; z < width; z++) {
				if (map.data.containsKey(new HeatMapSparse2D.Position(minX + x, minZ + z))) {
					image.setRGB(x, z, Color.getHSBColor(50 * map.data.get(new HeatMapSparse2D.Position(minX + x, minZ + z)), 1, 1).getRGB());
				} else {
					image.setRGB(x, z, Color.white.getRGB());
				}
			}
		}

		File outputFile = new File(HMPlugin.plugin.getDataFolder(), fileName + ".bmp");
		try {
			ImageIO.write(image, "bmp", outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeDebugFile(HeatMap2D hm, String fileName){
		System.out.println("Debug!");
		File outputFile = new File(HMPlugin.plugin.getDataFolder(), fileName + ".txt");
		try(FileOutputStream stream = new FileOutputStream(outputFile)){
			for(HeatMap2D.ChunkPosition chunkPos:hm.data.keySet()){
				System.out.println("Writing " + chunkPos.x + ", " + chunkPos.z);
				stream.write(new byte[]{'C','h','u','n','k',':',' ', ' ',(byte) (chunkPos.x >> 24), (byte) (chunkPos.x >> 16), (byte) (chunkPos.x >> 8), (byte) chunkPos.x, (byte) (chunkPos.z >> 24), (byte) (chunkPos.z >> 16), (byte) (chunkPos.z >> 8), (byte) chunkPos.z});
				stream.write(hm.data.get(chunkPos));
			}
			stream.flush();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
