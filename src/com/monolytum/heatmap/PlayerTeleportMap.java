package com.monolytum.heatmap;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

;

public class PlayerTeleportMap extends HeatMapFiller implements Listener {

	public PlayerTeleportMap(HeatMapStorage map) {
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
		map.addAt(e.getFrom(), 1);
		map.addAt(e.getTo(), 1);
	}


}
