package com.monolytum.heatmap;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

;

public class PlayerTeleportMap extends AbstractHeatMap implements Listener {

	public PlayerTeleportMap(DataStorage map) {
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
	public void onPlayerTeleport(PlayerTeleportEvent e) {

	}


}
