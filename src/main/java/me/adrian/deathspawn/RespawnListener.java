package me.adrian.deathspawn;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public final class RespawnListener implements Listener {

    private final DeathSpawn plugin;

    public RespawnListener(DeathSpawn plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onRespawn(PlayerRespawnEvent event) {
        // Only deaths, not returning from the End
        if (event.getRespawnReason() != PlayerRespawnEvent.RespawnReason.DEATH) return;

        // Player has a working bed or respawn anchor -> don't touch it
        if (event.isBedSpawn() || event.isAnchorSpawn()) return;

        Location spawn = plugin.getDeathSpawn();
        if (spawn == null || spawn.getWorld() == null) return; // not set -> vanilla behaviour

        event.setRespawnLocation(spawn);
    }
}