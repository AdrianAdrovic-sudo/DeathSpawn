package me.adrian.deathspawn;

import org.bukkit.Location;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathSpawn extends JavaPlugin {

    private static final String KEY = "death-spawn";

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new RespawnListener(this), this);

        DeathSpawnCommand cmd = new DeathSpawnCommand(this);
        PluginCommand command = getCommand("deathspawn");
        if (command != null) {
            command.setExecutor(cmd);
            command.setTabCompleter(cmd);
        }

        getLogger().info("DeathSpawn enabled. Current point: " + getDeathSpawn());
    }

    public Location getDeathSpawn() {
        return getConfig().getLocation(KEY);
    }

    public void setDeathSpawn(Location location) {
        getConfig().set(KEY, location); // null removes it
        saveConfig();
    }
}