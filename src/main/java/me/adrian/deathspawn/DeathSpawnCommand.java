package me.adrian.deathspawn;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public final class DeathSpawnCommand implements TabExecutor {

    private static final List<String> SUBCOMMANDS = List.of("set", "clear", "info", "tp");
    private final DeathSpawn plugin;

    public DeathSpawnCommand(DeathSpawn plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) return false; // shows the usage from plugin.yml

        switch (args[0].toLowerCase()) {
            case "set" -> {
                if (!(sender instanceof Player player)) {
                    sender.sendMessage(Component.text("Only players can use this.", NamedTextColor.RED));
                    return true;
                }
                plugin.setDeathSpawn(player.getLocation());
                sender.sendMessage(Component.text("Death spawn set to " + format(player.getLocation()), NamedTextColor.GREEN));
            }
            case "clear" -> {
                plugin.setDeathSpawn(null);
                sender.sendMessage(Component.text("Death spawn cleared (vanilla respawn).", NamedTextColor.YELLOW));
            }
            case "info" -> {
                Location loc = plugin.getDeathSpawn();
                sender.sendMessage(loc == null
                        ? Component.text("No death spawn set.", NamedTextColor.YELLOW)
                        : Component.text("Death spawn: " + format(loc), NamedTextColor.AQUA));
            }
            case "tp" -> {
                Location loc = plugin.getDeathSpawn();
                if (!(sender instanceof Player player)) {
                    sender.sendMessage(Component.text("Only players can use this.", NamedTextColor.RED));
                } else if (loc == null) {
                    sender.sendMessage(Component.text("No death spawn set.", NamedTextColor.YELLOW));
                } else {
                    player.teleport(loc);
                }
            }
            default -> {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return SUBCOMMANDS.stream()
                    .filter(s -> s.startsWith(args[0].toLowerCase()))
                    .toList();
        }
        return List.of();
    }

    private String format(Location l) {
        return String.format("%s %.1f %.1f %.1f",
                l.getWorld() == null ? "?" : l.getWorld().getName(), l.getX(), l.getY(), l.getZ());
    }
}