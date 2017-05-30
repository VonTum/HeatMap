package com.monolytum.heatmap.IO;


import com.monolytum.heatmap.HeatMap;
import com.monolytum.heatmap.HeatMap2D;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
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

        int width = (maxX - minX + 1) * 16;
        int height = (maxZ - minX + 1) * 16;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int chunkZ = minZ; chunkZ <= maxZ; chunkZ++) { // <= of < ?, want chunk maxX, maxZ moet toch ook getekend worden,
            for (int chunkX = minX; chunkX <= maxX; chunkX++) {

                HeatMap2D.ChunkPosition chunkPosition = new HeatMap2D.ChunkPosition(chunkX, chunkZ);

                if (map.data.containsKey(chunkPosition)) { // schrijf de waarden naar de afbeelding chunk per chunk
                    byte[] blockValue = map.data.get(chunkPosition);
                    for (int x = 0; x < 16; x++) {
                        for (int z = 0; z < 16; z++) {
                            int index = 16 * z + x; // index in de 256 byte array
                            Color color = Color.getHSBColor(blockValue[index]*10, 1, 1); // geeft blockValue[index] een juiste int waarde terug (aantal keer over het block geweest)?
                            image.setRGB(16 * chunkX + x, 16 * chunkZ + z, color.getRGB()); // stel de pixelkleur in
                        }
                    }
                } else { // schrijf een witte pixel naar de afbeelding (dubble for lus herhaald, voor debugging, compressie gebeurt later)
                    for (int x = 0; x < 16; x++) {
                        for (int z = 0; z < 16; z++) {
                            image.setRGB(16 * chunkX + x, 16 * chunkZ + z, Color.white.getRGB());
                        }
                    }
                }
            }
        }
        
        File imgUri = HeatMap.plugin.getDataFolder();
        File outputFile = new File(imgUri, fileName + ".bmp");
        try {
            ImageIO.write(image, "bmp", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
