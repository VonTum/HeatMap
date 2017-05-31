package com.monolytum.heatmap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMovementMap extends AbstractHeatMap implements Listener {
	
	public PlayerMovementMap(DataStorage map) {
		super(map);
	}
	
	@Override
	void enable() {
		Bukkit.getPluginManager().registerEvents(this, HeatMap.plugin);
	}
	
	@Override
	void disable() {
		PlayerMoveEvent.getHandlerList().unregister(this);
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Location from = e.getFrom();
		Location to = e.getTo();
		
		if (!(from.getBlockX() == to.getBlockX() && from.getBlockY() == to.getBlockY() && from.getBlockZ() == to.getBlockZ())) {
			map.addAt(to, 1);
			e.getPlayer().sendMessage(String.format("(x1:%d z1:%d) (x2:%d z2:%d) -> v:%d", to.getBlockX(), to.getBlockZ(), to.getChunk().getX(), to.getChunk().getZ(), map.getValueAt(to)));
		}
	}
	
}
