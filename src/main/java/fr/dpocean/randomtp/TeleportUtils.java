package fr.dpocean.randomtp;

import java.util.HashSet;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class TeleportUtils {

    static RandomTP plugin;

    public TeleportUtils(RandomTP plugin) {
        this.plugin = plugin;
    }

    public static HashSet<Material> badBlocks = new HashSet<Material>();

    static {
        badBlocks.add(Material.LAVA);
        badBlocks.add(Material.FIRE);
        badBlocks.add(Material.STONE);
        badBlocks.add(Material.WATER);
        badBlocks.add(Material.STATIONARY_WATER);
        badBlocks.add(Material.STATIONARY_LAVA);
    }

    public static Location generateLocation(Player player) {
        Random random = new Random();
        int x = random.nextInt(plugin.getConfig().getInt("border")), y = 150, z = random.nextInt(plugin.getConfig().getInt("border"));
        Location randomLocation = new Location(player.getWorld(), x, y, z);

        randomLocation.setY(randomLocation.getWorld().getHighestBlockYAt(randomLocation));

        while (!isLocationSafe(randomLocation)) {
            randomLocation = generateLocation(player);
        }

        return randomLocation;
    }

    public static boolean isLocationSafe(Location location) {
        int x = location.getBlockX(), y = location.getBlockY(), z = location.getBlockZ();

        Block block = location.getWorld().getBlockAt(x, y, z);
        Block below = location.getWorld().getBlockAt(x, y - 1, z);
        Block above = location.getWorld().getBlockAt(x, y + 1, z);
        
        return !(badBlocks.contains(below.getType()) || (block.getType().isSolid()) || (above.getType().isSolid()));
    }

}
